package info.unbelievable9.shiro.realm.dao;

import info.unbelievable9.shiro.realm.entity.SysRole;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysRoleDao {

    public SysRole createSysRole(SysRole sysRole);
    public void deleteSysRole(Long sysRoleId);

    public void correlateSysPermissions(Long sysRoleId, Long... sysPermissionIds);
    public void disassociateSysPermission(Long sysRoleId, Long... sysPermissionIds);
}
