package info.unbelievable9.shiro.authentication.strategy;

import info.unbelievable9.shiro.common.BaseTest;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.PrincipalCollection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
class AuthenticationStrategyTest extends BaseTest {

    @Test
    void testAllSuccessfulStrategy() {
        login("classpath:shiro/authentication/strategy/shiro-all-successful-strategy-success.ini", "Jack", "19901017");

        // Should Have 2 Correct Principals
        PrincipalCollection collection = subject().getPrincipals();

        Assertions.assertEquals(2, collection.asList().size());
    }

    @Test
    void testAllSuccessfulStrategyWithException() {
        Assertions.assertThrows(UnknownAccountException.class, () -> login("classpath:shiro/authentication/strategy/shiro-all-successful-strategy-fail.ini", "Jack", "19901017"));
    }

    @Test
    void testAtLeastOneSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-at-least-one-successful-strategy-success.ini", "Jack", "19901017");

        // Should Have 2 Correct Principal
        PrincipalCollection collection = subject().getPrincipals();

        Assertions.assertEquals(2, collection.asList().size());
    }

    @Test
    void testAtLeastOneSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-at-least-one-successful-strategy-fail.ini", "Jack", "19901017"));
    }

    @Test
    void testFirstSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-first-successful-strategy-success.ini", "Jack", "19901017");

        // Always Have 1 Correct Principal
        PrincipalCollection collection = subject().getPrincipals();

        Assertions.assertEquals(1, collection.asList().size());
    }

    @Test
    void testFirstSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-first-successful-strategy-fail.ini", "Jack", "19901017"));
    }

    @Test
    void testOnlyOneSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-only-one-successful-strategy-success.ini", "Jack", "19901017");

        // Always Have 1 Correct Principal
        PrincipalCollection collection = subject().getPrincipals();

        Assertions.assertEquals(1, collection.asList().size());
    }

    @Test
    void testOnlyOneSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-only-one-successful-strategy-fail.ini", "Jack", "19901017"));
    }
}
