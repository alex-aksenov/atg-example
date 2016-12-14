package gd.service.strategy.item;

import gd.dto.ItemDto;
import gd.service.strategy.ServiceActionStrategy;

/**
 * @author Alex Aksenov on 14/12/2016.
 **/
public interface ItemActionsStrategy extends ServiceActionStrategy<ItemDto>{

    enum Type {
        ORDER_MANAGER, FORM_HANDLER
    }

}
