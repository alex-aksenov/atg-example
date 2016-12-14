package gd.validate;

import gd.exception.ValidationException;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public abstract class Validator<T> {

    public abstract ValidationResult validate(T checkItem);

    public static void throwValidationEx(Object obj) throws ValidationException {

        String msg;

        if (obj instanceof Throwable) {
            msg = ((Throwable) obj).getMessage();
        } else {
            msg = obj != null ? obj.toString() : "null msg";
        }

        throw new ValidationException(new ValidationProblem(ValidationProblem.Level.ERROR, msg));
    }
}
