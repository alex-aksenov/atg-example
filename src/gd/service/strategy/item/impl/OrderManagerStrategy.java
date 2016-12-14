package gd.service.strategy.item.impl;

import atg.commerce.CommerceException;
import atg.commerce.order.CommerceItem;
import atg.commerce.order.CommerceItemNotFoundException;
import atg.commerce.order.OrderHolder;
import atg.commerce.order.OrderManager;
import atg.core.util.StringUtils;
import gd.dto.ItemDto;
import gd.exception.ValidationException;
import gd.service.strategy.item.ItemActionsStrategy;

import java.util.List;

import static gd.validate.Validator.throwValidationEx;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class OrderManagerStrategy implements ItemActionsStrategy {

    private OrderHolder shoppingCart;

    public OrderManagerStrategy(OrderHolder shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    @Override
    public void executeSave(ItemDto itemDto) throws ValidationException {
        try {
            OrderManager orderManager = shoppingCart.getOrderManager();

            String itemType = !StringUtils.isBlank(itemDto.getItemType()) ? itemDto.getItemType() :
                    orderManager.getOrderTools().getDefaultCommerceItemType();

            CommerceItem itemInfo = orderManager.getCommerceItemManager().createCommerceItem(
                    itemType, itemDto.getSkuId(), itemDto.getProductId(), itemDto.getQuantity());

            try {
                List<CommerceItem> items = shoppingCart.getCurrent()
                        .getCommerceItemsByCatalogRefId(itemDto.getSkuId());
                CommerceItem firstItem = items.get(0);
                firstItem.setQuantity(firstItem.getQuantity() + itemDto.getQuantity());
            } catch (CommerceItemNotFoundException notFound) {
                shoppingCart.getCurrent().addCommerceItem(itemInfo);
            }

        } catch (CommerceException e) {
            throwValidationEx(e.getMessage());
        }
    }
}
