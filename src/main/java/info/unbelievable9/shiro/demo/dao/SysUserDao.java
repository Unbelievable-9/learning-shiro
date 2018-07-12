package info.unbelievable9.shiro.demo.dao;

import info.unbelievable9.shiro.demo.entity.SysUser;

import java.util.Set;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public interface SysUserDao {

    public SysUser createSysUser(SysUser sysUser);
    public void updateSysUser(SysUser sysUser);
    public void deleteSysUser(Long sysUserId);

    public void correlateSysRoles(Long sysUserId, Long... sysRoleIds);
    public void disassociateSysRoles(Long sysUserId, Long... sysRoleIds);

    public SysUser findById(Long sysUserId);
    public SysUser findByUsername(String username);

    public Set<String> findAllSysRolesByUsername(String username);
    public Set<String> findAllSysPermissionsByUsername(String username);
}
