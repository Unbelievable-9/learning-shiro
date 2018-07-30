package info.unbelievable9.shiro.demo;

import info.unbelievable9.shiro.common.SysRealmBaseTest;
import lombok.extern.apachecommons.CommonsLog;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.function.Supplier;

/**
 * Created on : 2018/7/12
 * Author     : Unbelievable9
 **/
@CommonsLog
class SysUserRealmTest extends SysRealmBaseTest {

    @Test
    void testLoginSuccess() {
        login("classpath:shiro/demo/shiro.ini", sysuser2.getUsername(), sysUser2PlainTextPassword);

        Assertions.assertTrue(subject().isAuthenticated());
    }

    @Test
    void testLoginFailWithUnknownUsername() {
        Assertions.assertThrows(UnknownAccountException.class,
                 () -> login("classpath:shiro/demo/shiro.ini", sysuser3.getUsername() + "test", sysUser3PlainTextPassword));
    }

    @Test
    void testLoginFailWithIncorrectCredentials() {
        Assertions.assertThrows(IncorrectCredentialsException.class,
                () -> login("classpath:shiro/demo/shiro.ini", sysuser3.getUsername(), sysUser2PlainTextPassword));
    }

    @Test
    void testLoginFailWithLockedAccount() {
        Assertions.assertThrows(LockedAccountException.class,
                () -> login("classpath:shiro/demo/shiro.ini", sysuser4.getUsername(), sysUser4PlainTextPassword));
    }

    @Test
    void testLoginFailWithExcessiveAttempts() {
        for (int i = 0; i < 5; i++) {
            try {
                login("classpath:shiro/demo/shiro.ini", sysuser1.getUsername(), sysUser1PlainTextPassword + "test");
            } catch (Exception e) {
                int finalI = i + 1;
                Supplier<String> supplier = () -> "Retry Count: " + Integer.valueOf(finalI).toString();

                log.info(supplier);
            }
        }

        Assertions.assertThrows(ExcessiveAttemptsException.class,
                () -> login("classpath:shiro/demo/shiro.ini", sysuser1.getUsername(), sysUser1PlainTextPassword + "test"));
    }

    @Test
    void testRole() {
        login("classpath:shiro/demo/shiro.ini", sysuser2.getUsername(), sysUser2PlainTextPassword);
        Assertions.assertTrue(subject().hasRole("user"));
    }

    @Test
    void testPermission() {
        login("classpath:shiro/demo/shiro.ini", sysuser3.getUsername(), sysUser3PlainTextPassword);
        Assertions.assertTrue(subject().isPermitted("user:invite"));
    }
}
