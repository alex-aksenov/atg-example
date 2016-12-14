package gd.service;

import gd.dto.LoginDto;
import gd.service.strategy.ServiceActionStrategy;
import gd.service.strategy.login.LoginActionsStrategy;
import gd.service.strategy.login.StrategyFactory;

/**
 * @author Alex Aksenov on 12/12/2016.
 **/
public class LoginService extends StrategyDrivenService<LoginDto> {

    private StrategyFactory strategyFactory;

    public void logout(LoginActionsStrategy strategyByType) {
        strategyByType.logout();
    }

    @Override
    protected ServiceActionStrategy<LoginDto> getDefaultStrategy() {
        return strategyFactory.getDefaultStrategy();
    }

    public StrategyFactory getStrategyFactory() {
        return strategyFactory;
    }

    public void setStrategyFactory(StrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    public void logout(ServiceActionStrategy<LoginDto> strategyByType) {

    }
}
