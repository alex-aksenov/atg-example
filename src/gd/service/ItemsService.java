package gd.service;

import atg.commerce.order.CommerceItem;
import atg.commerce.order.OrderHolder;
import gd.dto.ItemDto;
import gd.service.strategy.ServiceActionStrategy;
import gd.service.strategy.item.StrategyFactory;

import java.util.List;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ItemsService extends StrategyDrivenService<ItemDto> {

    private OrderHolder shoppingCart;

    private StrategyFactory strategyFactory;

    public List<ItemDto> getItems() {
        return ItemDto.listFromCommerceItems(shoppingCart.getCurrent().getCommerceItems());
    }

    public List<CommerceItem> getItemsAsCommerceItems() {
        return shoppingCart.getCurrent().getCommerceItems();
    }

    public OrderHolder getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(OrderHolder shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public StrategyFactory getStrategyFactory() {
        return strategyFactory;
    }

    public void setStrategyFactory(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @Override
    protected ServiceActionStrategy<ItemDto> getDefaultStrategy() {
        return strategyFactory.getDefaultStrategy();
    }
}
