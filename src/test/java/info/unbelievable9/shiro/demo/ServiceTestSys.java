package info.unbelievable9.shiro.demo;

import info.unbelievable9.shiro.common.SysRealmBaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
class ServiceTestSys extends SysRealmBaseTest {

    @Test
    void testSystemCorrelation() {
        // Jack
        Set<String> sysUser1Roles = sysUserService.findAllRolesByUsername(sysuser1.getUsername());
        Assertions.assertEquals(2, sysUser1Roles.size());
        Assertions.assertTrue(sysUser1Roles.contains(sysRole1.getRole()));

        Set<String> sysUser1Permission = sysUserService.findAllPermissionsByUsername(sysuser1.getUsername());
        Assertions.assertEquals(3, sysUser1Permission.size());
        Assertions.assertTrue(sysUser1Permission.contains(sysPermission3.getPermission()));

        // Martin
        Set<String> sysUser4Roles = sysUserService.findAllRolesByUsername(sysuser4.getUsername());
        Assertions.assertEquals(1, sysUser4Roles.size());

        Set<String> sysUser4Permissions = sysUserService.findAllPermissionsByUsername(sysuser4.getUsername());
        Assertions.assertEquals(1, sysUser4Permissions.size());

        // Disable Invite User Permission
        sysRoleService.disassociatePermissions(sysRole2.getId(), sysPermission3.getId());
        sysUser4Permissions = sysUserService.findAllPermissionsByUsername(sysuser4.getUsername());
        Assertions.assertFalse(sysUser4Permissions.contains(sysPermission3.getPermission()));

        // Delete Invite User Permission
        sysPermissionService.deleteSysPermission(sysPermission3.getId());
        sysUser4Permissions = sysUserService.findAllPermissionsByUsername(sysuser4.getUsername());
        Assertions.assertEquals(0, sysUser4Permissions.size());

        // Remove Jack's Admin Role
        sysUserService.disassociateRoles(sysuser1.getId(), sysRole1.getId());
        sysUser1Roles = sysUserService.findAllRolesByUsername(sysuser1.getUsername());
        Assertions.assertEquals(1, sysUser1Roles.size());
    }
}
