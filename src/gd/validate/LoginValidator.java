package gd.validate;

import gd.dto.LoginDto;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Aksenov on 14/12/2016.
 **/
public class LoginValidator extends Validator<LoginDto> {

    @Override
    public ValidationResult validate(LoginDto loginDto) {
        List<ValidationProblem> problemList = new ArrayList<ValidationProblem>();

        return new ValidationResult(problemList);
    }
}