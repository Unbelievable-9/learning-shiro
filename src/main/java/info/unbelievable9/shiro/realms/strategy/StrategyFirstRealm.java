package info.unbelievable9.shiro.realms.strategy;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
public class StrategyFirstRealm implements Realm {

    @Override
    public String getName() {
        return "strategy_first_realm";
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

        return new SimpleAuthenticationInfo(principal, credentials, getName());
    }
}
