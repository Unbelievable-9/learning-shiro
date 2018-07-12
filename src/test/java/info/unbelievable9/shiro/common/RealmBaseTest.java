package info.unbelievable9.shiro.common;

import info.unbelievable9.shiro.realm.entity.SysPermission;
import info.unbelievable9.shiro.realm.entity.SysRole;
import info.unbelievable9.shiro.realm.entity.SysUser;
import info.unbelievable9.shiro.realm.service.*;
import info.unbelievable9.shiro.realm.utils.JdbcTemplateUtil;
import org.junit.jupiter.api.BeforeEach;

/**
 * Created on : 2018/7/12
 * Author     : Unbelievable9
 **/
public class RealmBaseTest {

    // Services
    protected SysUserService sysUserService = new SysUserServiceImpl();
    protected SysRoleService sysRoleService = new SysRoleServiceImpl();
    protected SysPermissionService sysPermissionService = new SysPermissionServiceImpl();

    // System Users
    protected SysUser sysuser1;
    protected SysUser sysuser2;
    protected SysUser sysuser3;
    protected SysUser sysuser4;

    // System Roles
    protected SysRole sysRole1;
    protected SysRole sysRole2;

    // System Permissions
    protected SysPermission sysPermission1;
    protected SysPermission sysPermission2;
    protected SysPermission sysPermission3;

    @BeforeEach
    public void setup () {
        // Cleanup
        JdbcTemplateUtil.jdbcTemplate().update("truncate table sys_users");
        JdbcTemplateUtil.jdbcTemplate().update("truncate table sys_roles");
        JdbcTemplateUtil.jdbcTemplate().update("truncate table sys_permissions");
        JdbcTemplateUtil.jdbcTemplate().update("truncate table sys_users_roles");
        JdbcTemplateUtil.jdbcTemplate().update("truncate table sys_roles_permissions");
        // Create Users
        sysuser1 = new SysUser("Jack", "19901017");
        sysuser2 = new SysUser("Gia", "19910130");
        sysuser3 = new SysUser("Joe", "19910516");
        sysuser4 = new SysUser("Martin", "19901103");
        sysuser4.setLocked(Boolean.TRUE);

        sysUserService.createSysUser(sysuser1);
        sysUserService.createSysUser(sysuser2);
        sysUserService.createSysUser(sysuser3);
        sysUserService.createSysUser(sysuser4);

        // Create Roles
        sysRole1 = new SysRole("admin", "管理员", Boolean.TRUE);
        sysRole2 = new SysRole("user", "用户", Boolean.TRUE);

        sysRoleService.createSysRole(sysRole1);
        sysRoleService.createSysRole(sysRole2);

        // Create Permission
        sysPermission1 = new SysPermission("user:create", "用户模块-新增", Boolean.TRUE);
        sysPermission2 = new SysPermission("user:update", "用户模块-修改", Boolean.TRUE);
        sysPermission3 = new SysPermission("user:invite", "用户模块-邀请", Boolean.TRUE);

        sysPermissionService.createSysPermission(sysPermission1);
        sysPermissionService.createSysPermission(sysPermission2);
        sysPermissionService.createSysPermission(sysPermission3);

        // Correlate System Roles to System User
        sysUserService.correlateRoles(sysuser1.getId(), sysRole1.getId(), sysRole2.getId());
        sysUserService.correlateRoles(sysuser2.getId(), sysRole2.getId());
        sysUserService.correlateRoles(sysuser3.getId(), sysRole2.getId());
        sysUserService.correlateRoles(sysuser4.getId(), sysRole2.getId());

        // Correlate System Permissions To System Role
        sysRoleService.correlateSysPermissions(sysRole1.getId(), sysPermission1.getId(), sysPermission2.getId(), sysPermission3.getId());
        sysRoleService.correlateSysPermissions(sysRole2.getId(), sysPermission3.getId());
    }
}
