package info.unbelievable9.shiro.realm.dao;

import info.unbelievable9.shiro.realm.entity.SysPermission;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysPermissionDao {

    public SysPermission createSysPermission(SysPermission sysPermission);
    public void deleteSysPermission(Long sysPermissionId);
}
