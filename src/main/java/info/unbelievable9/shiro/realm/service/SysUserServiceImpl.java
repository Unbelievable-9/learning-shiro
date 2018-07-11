package info.unbelievable9.shiro.realm.service;

import info.unbelievable9.shiro.realm.dao.SysUserDao;
import info.unbelievable9.shiro.realm.dao.SysUserDaoImpl;
import info.unbelievable9.shiro.realm.entity.SysUser;
import info.unbelievable9.shiro.realm.helper.PasswordHelper;

import java.util.Set;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class SysUserServiceImpl implements SysUserService {

    private SysUserDao sysUserDao = new SysUserDaoImpl();

    private PasswordHelper passwordHelper = new PasswordHelper();

    @Override
    public SysUser createSysUser(SysUser sysUser) {
        passwordHelper.encrypt(sysUser, "md5", 2);

        return sysUserDao.createSysUser(sysUser);
    }

    @Override
    public void changeSysUserPassword(Long sysUserId, String newPassword) {
        SysUser sysUser = sysUserDao.findById(sysUserId);

        if (sysUser != null) {
            sysUser.setPassword(newPassword);
            passwordHelper.encrypt(sysUser, "md5", 2);

            sysUserDao.updateSysUser(sysUser);
        }
    }

    @Override
    public void correlateRoles(Long sysUserId, Long... sysRoleIds) {
        SysUser sysUser = sysUserDao.findById(sysUserId);

        if (sysUser != null) {
            sysUserDao.correlateSysRoles(sysUserId, sysRoleIds);
        }
    }

    @Override
    public void disassociateRoles(Long sysUserId, Long... sysRoleIds) {
        SysUser sysUser = sysUserDao.findById(sysUserId);

        if (sysUser != null) {
            sysUserDao.disassociateSysRoles(sysUserId, sysRoleIds);
        }
    }

    @Override
    public SysUser findByUsername(String username) {
        return sysUserDao.findByUsername(username);
    }

    @Override
    public Set<String> findAllRolesByUsername(String username) {
        return sysUserDao.findAllSysRolesByUsername(username);
    }

    @Override
    public Set<String> findAllPermissionsByUsername(String username) {
        return sysUserDao.findAllSysPermissionsByUsername(username);
    }
}
