package gd.service.strategy.item.impl;

import atg.commerce.order.purchase.CartModifierFormHandler;
import atg.core.util.StringUtils;
import atg.servlet.ServletUtil;
import gd.dto.ItemDto;
import gd.exception.ValidationException;
import gd.service.strategy.item.ItemActionsStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

import static gd.validate.Validator.throwValidationEx;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class FormHandlerStrategy implements ItemActionsStrategy {

    private final CartModifierFormHandler formHandler;

    public FormHandlerStrategy(CartModifierFormHandler formHandler) {
        this.formHandler = formHandler;
    }

    @Override
    public void executeSave(ItemDto itemDto) throws ValidationException {
        try {
            if (!StringUtils.isBlank(itemDto.getItemType())) {
                formHandler.setCommerceItemType(itemDto.getItemType());
            }

            formHandler.setCatalogRefIds(new String[]{itemDto.getSkuId()});
            formHandler.setProductId(itemDto.getProductId());
            formHandler.setQuantity(itemDto.getQuantity());

            formHandler.handleAddItemToOrder(ServletUtil.getCurrentRequest(),
                    ServletUtil.getCurrentResponse());

            if (formHandler.getFormError()) {
                for (Object o : formHandler.getFormExceptions()) {
                    throwValidationEx(o.toString());
                }
            }
        } catch (ServletException e) {
            throwValidationEx(e.getMessage());
        } catch (IOException e) {
            throwValidationEx(e.getMessage());
        }
    }
}
