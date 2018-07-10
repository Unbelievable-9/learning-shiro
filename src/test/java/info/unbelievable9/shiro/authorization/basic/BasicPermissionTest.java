package info.unbelievable9.shiro.authorization.basic;

import info.unbelievable9.shiro.common.BaseTest;
import org.apache.shiro.authz.UnauthorizedException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Created on : 2018/7/10
 * Author     : Unbelievable9
 **/
class BasicPermissionTest extends BaseTest {

    @Test
    void testIsPermitted() {
        login("classpath:shiro/authorization/basic/shiro-permission.ini", "Jack", "19901017");

        Assertions.assertTrue(subject().isPermitted("system:start"));

        Assertions.assertTrue(subject().isPermittedAll("system:start", "game:stop"));

        Assertions.assertFalse(subject().isPermittedAll("code:write"));
    }

    @Test
    void testCheckPermission() {
        login("classpath:shiro/authorization/basic/shiro-permission.ini", "Gia", "19910130");

        subject().checkPermission("game:start");

        subject().checkPermissions("game:*");

        Assertions.assertThrows(UnauthorizedException.class, () -> subject().checkPermission("system:stop"));
    }
}
