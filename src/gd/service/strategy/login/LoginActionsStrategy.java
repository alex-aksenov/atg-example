package gd.service.strategy.login;

import gd.dto.LoginDto;
import gd.service.strategy.ServiceActionStrategy;

/**
 * @author Alex Aksenov on 14/12/2016.
 **/
public interface LoginActionsStrategy extends ServiceActionStrategy<LoginDto> {

    enum Type {
        LOGIN_SERVICE, FORM_HANDLER
    }

    void logout();
}
