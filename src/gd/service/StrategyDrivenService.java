package gd.service;

import gd.exception.ValidationException;
import gd.service.strategy.ServiceActionStrategy;
import gd.validate.ValidationResult;
import gd.validate.Validator;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public abstract class StrategyDrivenService<T> {

    protected Validator<T> validator;

    public ValidationResult validate(T dto) {
        return validator.validate(dto);
    }

    public void validateAndSave(T dto, ServiceActionStrategy<T> strategy) throws ValidationException {
        ValidationResult vRes = validator.validate(dto);
        if (!vRes.hasErrors()) {
            strategy.executeSave(dto);
        } else {
            throw new ValidationException(vRes.getProblems());
        }
    }

    protected abstract ServiceActionStrategy<T> getDefaultStrategy();

    public Validator<T> getValidator() {
        return validator;
    }

    public void setValidator(Validator<T> validator) {
        this.validator = validator;
    }
}
