package info.unbelievable9.shiro.basic;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.function.Supplier;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
class MultiRealmsLoginTest {

    private static final Logger logger = LoggerFactory.getLogger(MultiRealmsLoginTest.class);

    @Test
    void shouldLogin() {
        // Initiate Security Manager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro/basic/shiro-multi-realms.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Setup Username & Password
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("Gia", "19910130");

        // Login
        try {
            subject.login(token);
        } catch (UnknownAccountException | IncorrectCredentialsException e) {
            Supplier<String> supplier = () -> e.getClass().toString() + " - Login Failed.";

            logger.error(supplier);
        }

        Assertions.assertTrue(subject.isAuthenticated());

        // Logout
        subject.logout();
    }
}
