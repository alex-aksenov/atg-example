package gd.validate;

import gd.dto.ItemDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class ItemValidator extends Validator<ItemDto> {

    @Override
    public ValidationResult validate(ItemDto checkItem) {
        List<ValidationProblem> problemList = new ArrayList<ValidationProblem>();

        if (checkItem.getSkuId() == null) {
            problemList.add(new ValidationProblem(
                    ValidationProblem.Level.ERROR, "SkuId must not be null!"));
        }

        if (checkItem.getQuantity() == null || checkItem.getQuantity() == 0) {
            problemList.add(new ValidationProblem(
                    ValidationProblem.Level.ERROR, "Quantity must not be null!"));
        } else {
            if (checkItem.getQuantity() > 10) {
                problemList.add(new ValidationProblem(
                        ValidationProblem.Level.WARNING, "Quantity is over 10!"));
            }
        }

        return new ValidationResult(problemList);
    }
}
