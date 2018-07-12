package info.unbelievable9.shiro.common;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.jupiter.api.AfterEach;

/**
 * Created on : 2018/7/9
 * Author     : Unbelievable9
 **/
public class BaseTest {

    /**
     * System User Login
     *
     * @param configFilePath Shiro Config File Path
     * @param username       Username
     * @param password       Plain Text Password
     */
    protected void login(String configFilePath, String username, String password) {
        // Initiate Security Manager
        Factory<SecurityManager> factory = new IniSecurityManagerFactory(configFilePath);
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);

        // Setup Username & Password
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // Login
        subject.login(token);
    }

    /**
     * Retrieve Current Shiro Subject
     *
     * @return Shiro Subject
     */
    protected Subject subject() {
        return SecurityUtils.getSubject();
    }

    @AfterEach
    public void tearDown() {
        ThreadContext.unbindSubject();
    }
}
