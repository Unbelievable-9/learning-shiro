package info.unbelievable9.shiro.authentication.strategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
class AuthenticationStrategyTest {

    private void login(String configFilePath) {
        // Initiate Security Manager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFilePath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Setup Username & Password
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Jack", "19901017");

        // Login
        subject.login(token);
    }

    @Test
    void testAllSuccessfulStrategy() {
        login("classpath:shiro/authentication/strategy/shiro-all-successful-strategy-success.ini");

        // Should Have 2 Correct Principals
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();

        Assertions.assertEquals(2, collection.asList().size());
    }

    @Test
    void testAllSuccessfulStrategyWithException() {
        Assertions.assertThrows(UnknownAccountException.class, () -> login("classpath:shiro/authentication/strategy/shiro-all-successful-strategy-fail.ini"));
    }

    @Test
    void testAtLeastOneSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-at-least-one-successful-strategy-success.ini");

        // Should Have 2 Correct Principal
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();

        Assertions.assertEquals(2, collection.asList().size());
    }

    @Test
    void testAtLeastOneSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-at-least-one-successful-strategy-fail.ini"));
    }

    @Test
    void testFirstSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-first-successful-strategy-success.ini");

        // Always Have 1 Correct Principal
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();

        Assertions.assertEquals(1, collection.asList().size());
    }

    @Test
    void testFirstSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-first-successful-strategy-fail.ini"));
    }

    @Test
    void testOnlyOneSuccessful() {
        login("classpath:shiro/authentication/strategy/shiro-only-one-successful-strategy-success.ini");

        // Always Have 1 Correct Principal
        Subject subject = SecurityUtils.getSubject();
        PrincipalCollection collection = subject.getPrincipals();

        Assertions.assertEquals(1, collection.asList().size());
    }

    @Test
    void testOnlyOneSuccessfulWithException() {
        Assertions.assertThrows(AuthenticationException.class, () -> login("classpath:shiro/authentication/strategy/shiro-only-one-successful-strategy-fail.ini"));
    }

    @AfterEach
    void tearDown() {
        ThreadContext.unbindSubject();
    }
}
