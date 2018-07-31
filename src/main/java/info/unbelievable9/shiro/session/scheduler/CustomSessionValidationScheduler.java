package info.unbelievable9.shiro.session.scheduler;

import info.unbelievable9.shiro.common.JdbcTemplateUtil;
import info.unbelievable9.shiro.common.SerializableUtil;
import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.util.TimeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomSessionValidationScheduler implements SessionValidationScheduler, Runnable {

    private JdbcTemplate jdbcTemplate = JdbcTemplateUtil.jdbcTemplate();

    ValidatingSessionManager sessionManager;
    private ScheduledExecutorService service;
    private long interval = DefaultSessionManager.DEFAULT_SESSION_VALIDATION_INTERVAL;
    private boolean enabled = false;
    private String threadNamePrefix = "CustomSessionValidationThread-";

    public CustomSessionValidationScheduler() {
        super();
    }

    public CustomSessionValidationScheduler(ValidatingSessionManager sessionManager) {
        super();
        this.sessionManager = sessionManager;
    }

    public ValidatingSessionManager getSessionManager() {
        return sessionManager;
    }

    public void setSessionManager(ValidatingSessionManager sessionManager) {
        this.sessionManager = sessionManager;
    }

    public long getInterval() {
        return interval;
    }

    public void setInterval(long interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        log.info("Session Validation Scheduler Started.");

        long startTime = System.currentTimeMillis();

        String sql = "select session from sessions limit ?, ?";
        int start = 0;
        int size = 5;

        List<String> sessionStringList = jdbcTemplate.queryForList(sql, String.class, start, size);

        while (sessionStringList.size() > 0) {
            for (String sessionString : sessionStringList) {
                Session session = SerializableUtil.deserialize(sessionString);

                // Use Reflection to Get Validate Method
                Method validateMethod = ReflectionUtils.findMethod(AbstractValidatingSessionManager.class, "validate", Session.class, SessionKey.class);
                validateMethod.setAccessible(true);

                ReflectionUtils.invokeMethod(validateMethod, sessionManager, session, new DefaultSessionKey(session.getId()));
            }

            start += size;

            sessionStringList = jdbcTemplate.queryForList(sql, String.class, start, size);
        }

        long stopTime = System.currentTimeMillis();

        log.info("Session Validation Scheduler Completed In: " + TimeUtil.toSecs(stopTime - start) + " seconds.");
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void enableSessionValidation() {
        if (this.interval > 0L) {
            this.service = Executors.newSingleThreadScheduledExecutor(new ThreadFactory() {
                private final AtomicInteger count = new AtomicInteger(1);

                public Thread newThread(Runnable r) {
                    Thread thread = new Thread(r);
                    thread.setDaemon(true);
                    thread.setName(threadNamePrefix + this.count.getAndIncrement());
                    return thread;
                }
            });

            this.service.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.MILLISECONDS);
        }

        this.enabled = true;
    }

    @Override
    public void disableSessionValidation() {
        if (this.service != null) {
            this.service.shutdownNow();
        }

        this.enabled = false;
    }
}
