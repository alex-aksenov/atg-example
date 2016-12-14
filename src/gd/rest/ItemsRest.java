package gd.rest;

import atg.commerce.order.CommerceItem;
import atg.core.util.StringUtils;
import gd.dto.ItemDto;
import gd.service.ItemsService;
import gd.service.strategy.item.ItemActionsStrategy;
import gd.service.strategy.item.StrategyFactory;

import java.util.List;

import static gd.utils.WebUtils.withRespCode;

/**
 * @author Alex Aksenov on 12/12/2016.
 */
public class ItemsRest {

    private static final String SUCCESSFULLY_ADDED = "Successfully added";

    private ItemsService itemsService;

    private StrategyFactory strategyFactory;

    public List<ItemDto> getItems() {
        return itemsService.getItems();
    }

    public List<CommerceItem> getItemsAsCommerceItems() {
        return itemsService.getItemsAsCommerceItems();
    }

    public String addItem(String skuId, String itemType, String productId, Long quantity, String strategy) {
        ItemDto newItemDto = ItemDto.newBuilder()
                .setSkuId(skuId)
                .setItemType(itemType)
                .setQuantity(quantity)
                .setProductId(productId)
                .build();
        try {
            ItemActionsStrategy actionsStrategy = StringUtils.isBlank(strategy) ?
                    strategyFactory.getDefaultStrategy() :
                    strategyFactory.getStrategyByType(ItemActionsStrategy.Type.valueOf(strategy));

            itemsService.validateAndSave(newItemDto, actionsStrategy);

            return SUCCESSFULLY_ADDED;
        } catch (Exception e) {
            return withRespCode(500, e.getMessage());
        }
    }

    public ItemsService getItemsService() {
        return itemsService;
    }

    public void setItemsService(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    public StrategyFactory getStrategyFactory() {
        return strategyFactory;
    }

    public void setStrategyFactory(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }
}
