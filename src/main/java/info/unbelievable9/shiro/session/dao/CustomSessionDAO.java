package info.unbelievable9.shiro.session.dao;

import info.unbelievable9.shiro.common.JdbcTemplateUtil;
import info.unbelievable9.shiro.common.SerializableUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.CachingSessionDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.Serializable;
import java.util.List;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
public class CustomSessionDAO extends CachingSessionDAO {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession)session).isValid()) {
            // Session Expired
            return;
        }

        String sql = "update sessions set session = ? where id = ?";

        jdbcTemplate.update(sql, SerializableUtil.serialize(session), session.getId());
    }

    @Override
    protected void doDelete(Session session) {
        String sql = "delete from sessions where id = ?";

        jdbcTemplate.update(sql, session.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);

        String sql = "insert into sessions(id, session) values(?, ?)";

        jdbcTemplate.update(sql, sessionId, SerializableUtil.serialize(session));

        return session.getId();
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        String sql = "select session from sessions where id = ?";

        List<String> sessionStringList = jdbcTemplate.queryForList(sql, String.class, serializable);

        if (sessionStringList.size() == 0) {
            return null;
        }

        return SerializableUtil.deserialize(sessionStringList.get(0));
    }
}
