package info.unbelievable9.shiro.session.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListener;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomSessionListener implements SessionListener {

    @Override
    public void onStart(Session session) {
        log.info("Session Start.");
    }

    @Override
    public void onStop(Session session) {
        log.info("Session Expired/Stopped.");
    }

    @Override
    public void onExpiration(Session session) {
        log.info("Session Stopped.");
    }
}
