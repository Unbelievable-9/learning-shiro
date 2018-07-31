package info.unbelievable9.shiro.demo.dao;

import info.unbelievable9.shiro.common.JdbcTemplateUtil;
import info.unbelievable9.shiro.demo.entity.SysRole;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class SysRoleDaoImpl implements SysRoleDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    private boolean exist(Long sysRoleId, Long sysPermissionId) {
        final String sql = "select count(1) from sys_roles_permissions where role_id=? and permission_id=?";

        return jdbcTemplate.queryForObject(sql, Integer.class, sysRoleId, sysPermissionId) > 0;
    }

    @Override
    public SysRole createSysRole(SysRole sysRole) {
        final String sql = "insert into sys_roles(role, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, sysRole.getRole());
            preparedStatement.setString(2, sysRole.getDescription());
            preparedStatement.setBoolean(3, sysRole.getAvailable());

            return preparedStatement;
        }, keyHolder);

        sysRole.setId(keyHolder.getKey().longValue());

        return sysRole;
    }

    @Override
    public void deleteSysRole(Long sysRoleId) {
        String sql = "delete from sys_roles_permissions where role_id=?";
        jdbcTemplate.update(sql, sysRoleId);

        sql = "delete from sys_roles where id=?";
        jdbcTemplate.update(sql, sysRoleId);
    }

    @Override
    public void correlateSysPermissions(Long sysRoleId, Long... sysPermissionIds) {
        if (sysPermissionIds == null || sysPermissionIds.length == 0) {
            return;
        }

        final String sql = "insert into sys_roles_permissions(role_id, permission_id) values(?,?)";

        for (Long sysPermissionId : sysPermissionIds) {
            if (!exist(sysRoleId, sysPermissionId)) {
                jdbcTemplate.update(sql, sysRoleId, sysPermissionId);
            }
        }
    }

    @Override
    public void disassociateSysPermission(Long sysRoleId, Long... sysPermissionIds) {
        if (sysPermissionIds == null || sysPermissionIds.length == 0) {
            return;
        }

        final String sql = "delete from sys_roles_permissions where role_id=? and permission_id=?";

        for (Long sysPermissionId : sysPermissionIds) {
            if (exist(sysRoleId, sysPermissionId)) {
                jdbcTemplate.update(sql, sysRoleId, sysPermissionId);
            }
        }
    }
}
