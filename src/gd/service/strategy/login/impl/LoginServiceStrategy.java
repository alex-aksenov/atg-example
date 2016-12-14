package gd.service.strategy.login.impl;

import atg.userprofiling.ProfileServices;
import gd.dto.LoginDto;
import gd.exception.ValidationException;
import gd.service.strategy.login.LoginActionsStrategy;

import static gd.validate.Validator.throwValidationEx;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class LoginServiceStrategy implements LoginActionsStrategy {

    private ProfileServices profileServices;

    public LoginServiceStrategy(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }

    @Override
    public void executeSave(LoginDto loginDto) throws ValidationException {
        if (profileServices.getCurrentProfile().isTransient()) {
            try {
                profileServices.loginUser(loginDto.getLogin(), loginDto.getPassword());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throwValidationEx(String.format("You are already logged in, %s.",
                    profileServices.getCurrentProfile().getItemDisplayName()));
        }
    }

    @Override
    public void logout() {
        try {
            profileServices.logoutUser();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
