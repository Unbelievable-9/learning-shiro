package info.unbelievable9.shiro.authentication.realms.strategy;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
public class StrategyThirdRealm implements Realm {

    @Override
    public String getName() {
        return "strategy_third_realm";
    }

    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        String credentials = new String((char[])authenticationToken.getCredentials());

        if (!principal.equals("Jack")) {
            throw new UnknownAccountException();
        }

        if (!credentials.equals("19901017")) {
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(principal + "@test.com", credentials, getName());
    }
}
