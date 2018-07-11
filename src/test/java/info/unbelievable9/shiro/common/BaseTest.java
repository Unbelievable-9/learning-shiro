package info.unbelievable9.shiro.common;

import info.unbelievable9.shiro.realm.service.*;
import info.unbelievable9.shiro.realm.utils.JdbcTemplateUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
public class BaseTest {

    // Services
    protected SysUserService sysUserService = new SysUserServiceImpl();
    protected SysRoleService sysRoleService = new SysRoleServiceImpl();
    protected SysPermissionService sysPermissionService = new SysPermissionServiceImpl();

    @BeforeEach
    public void setup() {
        // cleanup
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_users");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_roles");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_permissions");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_users_roles");
        JdbcTemplateUtil.jdbcTemplate().update("delete from sys_roles_permissions");


    }

    @AfterEach
    public void tearDown() {
        ThreadContext.unbindSubject();
    }

    protected void login(String configFilePath, String username, String password) {
        // Initiate Security Manager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFilePath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Setup Username & Password
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // Login
        subject.login(token);
    }

    protected Subject subject() {
        return SecurityUtils.getSubject();
    }
}
