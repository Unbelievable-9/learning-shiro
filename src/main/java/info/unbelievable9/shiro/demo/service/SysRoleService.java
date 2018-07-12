package info.unbelievable9.shiro.demo.service;

import info.unbelievable9.shiro.demo.entity.SysRole;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysRoleService {

    /**
     * Create System Role
     *
     * @param sysRole System Role Entity
     * @return System Role Entity With ID
     */
    public SysRole createSysRole(SysRole sysRole);

    /**
     * Delete System Role
     *
     * @param sysRoleId System Role ID
     */
    public void deleteSysRole(Long sysRoleId);

    /**
     * Correlate System Permissions to System Role
     *
     * @param sysRoleId        System Role ID
     * @param sysPermissionIds System Permission IDs
     */
    public void correlateSysPermissions(Long sysRoleId, Long... sysPermissionIds);

    /**
     * Disassociate System Permissions From System Role
     *
     * @param sysRoleId        System Role ID
     * @param sysPermissionIds System Permission IDs
     */
    public void disassociatePermissions(Long sysRoleId, Long... sysPermissionIds);
}
