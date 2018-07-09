package info.unbelievable9.shiro.authentication.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
public class OnlyOneSuccessfulStrategy extends AbstractAuthenticationStrategy {

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {
        String msg;
        if (singleRealmInfo == null) {
            return aggregateInfo;
        } else {
            if (aggregateInfo == null) {
                return singleRealmInfo;
            } else {
                AuthenticationInfo mergedInfo = merge(singleRealmInfo, aggregateInfo);

                if (mergedInfo.getPrincipals().getRealmNames().size() > 1) {
                    msg = "Find more than one valid account data. Please ensure only one valid account in configuration.";
                    throw new AuthenticationException(msg);
                } else {
                    return mergedInfo;
                }
            }
        }
    }
}
