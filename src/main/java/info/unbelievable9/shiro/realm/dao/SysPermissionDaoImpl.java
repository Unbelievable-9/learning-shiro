package info.unbelievable9.shiro.realm.dao;

import info.unbelievable9.shiro.realm.entity.SysPermission;
import info.unbelievable9.shiro.realm.utils.JdbcTemplateUtil;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class SysPermissionDaoImpl implements SysPermissionDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    @Override
    public SysPermission createSysPermission(SysPermission sysPermission) {
        final String sql = "insert into sys_permissions(permission, description, available) values(?,?,?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, sysPermission.getPermission());
            preparedStatement.setString(2, sysPermission.getDescription());
            preparedStatement.setBoolean(3, sysPermission.getAvailable());

            return preparedStatement;
        }, keyHolder);

        sysPermission.setId(keyHolder.getKey().longValue());

        return sysPermission;
    }

    @Override
    public void deleteSysPermission(Long sysPermissionId) {
        String sql = "delete from sys_roles_permissions where permission_id=?";
        jdbcTemplate.update(sql, sysPermissionId);

        sql = "delete from sys_permissions where id=?";
        jdbcTemplate.update(sql, sysPermissionId);
    }
}
