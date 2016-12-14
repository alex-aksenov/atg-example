package gd.service.strategy.login.impl;

import atg.servlet.ServletUtil;
import atg.userprofiling.ProfileFormHandler;
import gd.dto.LoginDto;
import gd.exception.ValidationException;
import gd.service.strategy.login.LoginActionsStrategy;

import javax.servlet.ServletException;
import java.io.IOException;

import static gd.validate.Validator.throwValidationEx;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class FormHandlerStrategy implements LoginActionsStrategy {

    private final ProfileFormHandler formHandler;

    public FormHandlerStrategy(ProfileFormHandler formHandler) {
        this.formHandler = formHandler;
    }

    @Override
    public void executeSave(LoginDto loginDto) throws ValidationException {
        try {
            formHandler.getValueMap().put("login", loginDto.getLogin());
            formHandler.getValueMap().put("password", loginDto.getPassword());

            formHandler.handleLogin(ServletUtil.getCurrentRequest(), ServletUtil.getCurrentResponse());

            if (formHandler.getFormError()) {
                for (Object o : formHandler.getFormExceptions()) {
                    throwValidationEx(o.toString());
                }
            }
            formHandler.isValidSession(ServletUtil.getCurrentRequest());
        } catch (ServletException e) {
            throwValidationEx(e.getMessage());
        } catch (IOException e) {
            throwValidationEx(e.getMessage());
        }
    }

    @Override
    public void logout() {
        try {
            formHandler.handleLogout(ServletUtil.getCurrentRequest(), ServletUtil.getCurrentResponse());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
