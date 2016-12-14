package gd.service.strategy.item;

import atg.commerce.order.OrderHolder;
import atg.commerce.order.purchase.CartModifierFormHandler;
import gd.service.strategy.item.impl.FormHandlerStrategy;
import gd.service.strategy.item.impl.OrderManagerStrategy;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class StrategyFactory {

    private OrderHolder shoppingCart;

    private CartModifierFormHandler cartModifierFormHandler;

    public ItemActionsStrategy getStrategyByType(ItemActionsStrategy.Type type) {
        switch (type) {
            case ORDER_MANAGER:
                return new OrderManagerStrategy(shoppingCart);
            case FORM_HANDLER:
            default:
                return getDefaultStrategy();
        }
    }

    public ItemActionsStrategy getDefaultStrategy() {
        return new FormHandlerStrategy(cartModifierFormHandler);
    }

    public OrderHolder getShoppingCart() {
        return shoppingCart;
    }

    public void setShoppingCart(OrderHolder shoppingCart) {
        this.shoppingCart = shoppingCart;
    }

    public CartModifierFormHandler getCartModifierFormHandler() {
        return cartModifierFormHandler;
    }

    public void setCartModifierFormHandler(CartModifierFormHandler cartModifierFormHandler) {
        this.cartModifierFormHandler = cartModifierFormHandler;
    }
}
