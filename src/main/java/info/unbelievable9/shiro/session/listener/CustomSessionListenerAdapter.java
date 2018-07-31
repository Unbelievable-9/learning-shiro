package info.unbelievable9.shiro.session.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionListenerAdapter;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomSessionListenerAdapter extends SessionListenerAdapter {

    @Override
    public void onStart(Session session) {
        log.info("Adapter: Session Start.");
    }
}
