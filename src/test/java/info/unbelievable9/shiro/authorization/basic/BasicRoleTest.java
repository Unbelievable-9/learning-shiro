package info.unbelievable9.shiro.authorization.basic;

import info.unbelievable9.shiro.common.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
class BasicRoleTest extends BaseTest {

    @Test
    void testHasRole() {
        login("classpath:shiro/authorization/basic/shiro-role.ini", "Jack", "19901017");

        Assertions.assertTrue(subject().hasRole("hacker"));

        Assertions.assertTrue(subject().hasAllRoles(Arrays.asList("hacker", "baller")));

        boolean[] hasRoleResult = subject().hasRoles(Arrays.asList("hacker", "baller", "gamer"));
        Assertions.assertTrue(hasRoleResult[0]);
        Assertions.assertTrue(hasRoleResult[1]);
        Assertions.assertFalse(hasRoleResult[2]);
    }
}
