package info.unbelievable9.shiro.demo.realm;

import info.unbelievable9.shiro.demo.entity.SysUser;
import info.unbelievable9.shiro.demo.service.SysUserService;
import info.unbelievable9.shiro.demo.service.SysUserServiceImpl;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * Created on : 2018/7/12
 * Author     : Unbelievable9
 **/
public class SysUserRealm extends AuthorizingRealm {

    private SysUserService sysUserService = new SysUserServiceImpl();

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(sysUserService.findAllRolesByUsername(principal));
        authorizationInfo.setStringPermissions(sysUserService.findAllPermissionsByUsername(principal));

        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();

        SysUser sysUser = sysUserService.findByUsername(principal);

        if (sysUser == null) {
            throw new UnknownAccountException();
        }

        if (sysUser.getLocked().equals(Boolean.TRUE)) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(
                sysUser.getUsername(),
                sysUser.getPassword(),
                ByteSource.Util.bytes(sysUser.getCredentialSalt()),
                getName()
        );
    }
}
