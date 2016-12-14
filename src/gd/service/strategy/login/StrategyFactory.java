package gd.service.strategy.login;

import atg.userprofiling.ProfileFormHandler;
import atg.userprofiling.ProfileServices;
import gd.dto.LoginDto;
import gd.service.strategy.ServiceActionStrategy;
import gd.service.strategy.login.impl.FormHandlerStrategy;
import gd.service.strategy.login.impl.LoginServiceStrategy;

/**
 * @author Alex Aksenov on 13/12/2016.
 **/
public class StrategyFactory {

    private ProfileFormHandler profileFormHandler;

    private ProfileServices profileServices;

    public LoginActionsStrategy getStrategyByType(LoginActionsStrategy.Type type) {
        switch (type) {
            case LOGIN_SERVICE:
                return new LoginServiceStrategy(profileServices);
            case FORM_HANDLER:
            default:
                return getDefaultStrategy();
        }
    }

    public LoginActionsStrategy getDefaultStrategy() {
        return new FormHandlerStrategy(profileFormHandler);
    }

    public ProfileFormHandler getProfileFormHandler() {
        return profileFormHandler;
    }

    public void setProfileFormHandler(ProfileFormHandler profileFormHandler) {
        this.profileFormHandler = profileFormHandler;
    }

    public ProfileServices getProfileServices() {
        return profileServices;
    }

    public void setProfileServices(ProfileServices profileServices) {
        this.profileServices = profileServices;
    }
}
