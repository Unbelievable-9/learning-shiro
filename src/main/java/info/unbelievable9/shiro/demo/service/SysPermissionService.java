package info.unbelievable9.shiro.demo.service;

import info.unbelievable9.shiro.demo.entity.SysPermission;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysPermissionService {

    /**
     * Create System Permission
     *
     * @param sysPermission System Permission Entity
     * @return System Permission Entity With ID
     */
    public SysPermission createSysPermission(SysPermission sysPermission);

    /**
     * Delete System Permission
     *
     * @param sysPermissionId System Permission ID
     */
    public void deleteSysPermission(Long sysPermissionId);
}
