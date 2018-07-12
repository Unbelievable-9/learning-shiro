package info.unbelievable9.shiro.demo.service;

import info.unbelievable9.shiro.demo.entity.SysUser;

import java.util.Set;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysUserService {

    /**
     * Add System User
     *
     * @param sysUser System User Entity
     * @return System User Entity With ID
     */
    public SysUser createSysUser(SysUser sysUser);

    /**
     * Change System User Password
     *
     * @param sysUserId   System User ID
     * @param newPassword New Password
     */
    public void changeSysUserPassword(Long sysUserId, String newPassword);

    /**
     * Correlate System Roles to System User
     *
     * @param sysUserId  System User ID
     * @param sysRoleIds System Role IDs
     */
    public void correlateRoles(Long sysUserId, Long... sysRoleIds);

    /**
     * Disassociate System Roles From System User
     *
     * @param sysUserId  System User ID
     * @param sysRoleIds System Role IDs
     */
    public void disassociateRoles(Long sysUserId, Long... sysRoleIds);

    /**
     * Find System User By Username
     *
     * @param username usernam e
     * @return System User Entity
     */
    public SysUser findByUsername(String username);

    /**
     * Find All System Role Correlations For System User By Username
     *
     * @param username username
     * @return Set of System Roles
     */
    public Set<String> findAllRolesByUsername(String username);

    /**
     * Find All System Permission Correlations For System User By Username
     *
     * @param username username
     * @return Set of System Permissions
     */
    public Set<String> findAllPermissionsByUsername(String username);
}
