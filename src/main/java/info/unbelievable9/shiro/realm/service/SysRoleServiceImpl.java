package info.unbelievable9.shiro.realm.service;

import info.unbelievable9.shiro.realm.dao.SysRoleDao;
import info.unbelievable9.shiro.realm.dao.SysRoleDaoImpl;
import info.unbelievable9.shiro.realm.entity.SysRole;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class SysRoleServiceImpl implements SysRoleService {

    private SysRoleDao sysRoleDao = new SysRoleDaoImpl();

    @Override
    public SysRole createSysRole(SysRole sysRole) {
        return sysRoleDao.createSysRole(sysRole);
    }

    @Override
    public void deleteSysRole(Long sysRoleId) {
        sysRoleDao.deleteSysRole(sysRoleId);
    }

    @Override
    public void correlateSysPermissions(Long sysRoleId, Long... sysPermissionIds) {
        sysRoleDao.correlateSysPermissions(sysRoleId, sysPermissionIds);
    }

    @Override
    public void disassociatePermissions(Long sysRoleId, Long... sysPermissionIds) {
        sysRoleDao.disassociateSysPermission(sysRoleId, sysPermissionIds);
    }
}
