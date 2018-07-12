package info.unbelievable9.shiro.demo.dao;

import info.unbelievable9.shiro.demo.entity.SysUser;
import info.unbelievable9.shiro.demo.utils.JdbcTemplateUtil;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class SysUserDaoImpl implements SysUserDao {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    private boolean exist(Long sysUserId, Long sysRoleId) {
        final String sql = "select count(1) from sys_users_roles where user_id=? and role_id=?";

        return jdbcTemplate.queryForObject(sql, Integer.class, sysUserId, sysRoleId) > 0;
    }

    @Override
    public SysUser createSysUser(SysUser sysUser) {
        final String sql = "insert into sys_users(username, password, salt, locked) values(?,?,?,?)";

        // SysUser ID Key Holder
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"id"});
            preparedStatement.setString(1, sysUser.getUsername());
            preparedStatement.setString(2, sysUser.getPassword());
            preparedStatement.setString(3, sysUser.getSalt());
            preparedStatement.setBoolean(4, sysUser.getLocked());

            return preparedStatement;
        }, keyHolder);

        sysUser.setId(keyHolder.getKey().longValue());

        return sysUser;
    }

    @Override
    public void updateSysUser(SysUser sysUser) {
        final String sql = "update sys_users set username=?, password=?, salt=?, locked=? where id=?";

        jdbcTemplate.update(sql, sysUser.getUsername(), sysUser.getPassword(), sysUser.getSalt(), sysUser.getId());
    }

    @Override
    public void deleteSysUser(Long sysUserId) {
        final String sql = "delete from sys_users where id=?";

        jdbcTemplate.update(sql, sysUserId);
    }

    @Override
    public void correlateSysRoles(Long sysUserId, Long... sysRoleIds) {
        if (sysRoleIds == null || sysRoleIds.length == 0) {
            return;
        }

        final String sql = "insert into sys_users_roles(user_id, role_id) values(?,?)";

        for (Long sysRoleId : sysRoleIds) {
            if (!exist(sysUserId, sysRoleId)) {
                jdbcTemplate.update(sql, sysUserId, sysRoleId);
            }
        }
    }

    @Override
    public void disassociateSysRoles(Long sysUserId, Long... sysRoleIds) {
        if (sysRoleIds == null || sysRoleIds.length == 0) {
            return;
        }

        final String sql = "delete from sys_users_roles where user_id=? and role_id=?";

        for (Long sysRoleId : sysRoleIds) {
            if (exist(sysUserId, sysRoleId)) {
                jdbcTemplate.update(sql, sysUserId, sysRoleId);
            }
        }
    }

    @Override
    public SysUser findById(Long sysUserId) {
        final String sql = "select id, username, password, salt, locked from sys_users where id=? ";

        List<SysUser> sysUserList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysUser.class), sysUserId);

        if (sysUserList.size() == 0) {
            return null;
        }

        return sysUserList.get(0);
    }

    @Override
    public SysUser findByUsername(String username) {
        final String sql = "select id, username, password, salt, locked from sys_users where username=?";

        List<SysUser> sysUserList = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(SysUser.class), username);

        if (sysUserList.size() == 0) {
            return null;
        }

        return sysUserList.get(0);
    }

    @Override
    public Set<String> findAllSysRolesByUsername(String username) {
        final String sql = "select role from sys_users u, sys_roles r,sys_users_roles ur where u.username=? and u.id=ur.user_id and r.id=ur.role_id";

        return new HashSet<>(jdbcTemplate.queryForList(sql, String.class, username));
    }

    @Override
    public Set<String> findAllSysPermissionsByUsername(String username) {
        final String sql = "select permission from sys_users u, sys_roles r, sys_permissions p, sys_users_roles ur, sys_roles_permissions rp where u.username=? and u.id = ur.user_id and r.id = ur.role_id and r.id = rp.role_id and p.id = rp.permission_id";

        return new HashSet<>(jdbcTemplate.queryForList(sql, String.class, username));
    }
}
