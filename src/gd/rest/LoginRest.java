package gd.rest;

import atg.core.util.StringUtils;
import atg.servlet.ServletUtil;
import gd.dto.LoginDto;
import gd.service.LoginService;
import gd.service.strategy.login.LoginActionsStrategy;
import gd.service.strategy.login.StrategyFactory;

import static gd.utils.WebUtils.withRespCode;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class LoginRest {

    private LoginService loginService;

    private StrategyFactory strategyFactory;

    public String login(String login, String password, String strategy) {
        LoginDto loginDto = new LoginDto(login, password);

        try {
            LoginActionsStrategy actionsStrategy = StringUtils.isBlank(strategy) ?
                    strategyFactory.getDefaultStrategy() :
                    strategyFactory.getStrategyByType(LoginActionsStrategy.Type.valueOf(strategy));

            loginService.validateAndSave(loginDto, actionsStrategy);

            return String.format("Hello %s, you have successfully logged in! Your sessionId is %s",
                    login, ServletUtil.getCurrentRequest().getRequestedSessionId());
        } catch (Exception e) {
            return withRespCode(500, e.getMessage());
        }
    }

    public String logout(String strategy) {
        try {
            LoginActionsStrategy actionsStrategy = StringUtils.isBlank(strategy) ?
                    strategyFactory.getDefaultStrategy() :
                    strategyFactory.getStrategyByType(LoginActionsStrategy.Type.valueOf(strategy));

            loginService.logout(actionsStrategy);

            return withRespCode(200, String.format("You have successfully logged out! Your " +
                    "sessionId is %s", ServletUtil.getCurrentRequest().getRequestedSessionId()));
        } catch (Exception e) {
            return withRespCode(500, e.getMessage());
        }
    }

    public LoginService getLoginService() {
        return loginService;
    }

    public void setLoginService(LoginService loginService) {
        this.loginService = loginService;
    }

    public StrategyFactory getStrategyFactory() {
        return strategyFactory;
    }

    public void setStrategyFactory(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }
}
