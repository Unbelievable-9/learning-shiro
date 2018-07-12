package info.unbelievable9.shiro.demo.utils;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created on : 2018/7/12
 * Author     : Unbelievable9
 **/
public class PasswordRetryLimitHashedCredentialsMatcher extends HashedCredentialsMatcher {

    private Ehcache passwordRetryLimitCache;

    public PasswordRetryLimitHashedCredentialsMatcher() {
        CacheManager cacheManager =
                CacheManager.newInstance(Objects.requireNonNull(CacheManager.class.getClassLoader().getResource("ehcache/ehcache.xml")));

        passwordRetryLimitCache = cacheManager.getCache("passwordRetryCache");
    }

    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        String principal = (String) token.getPrincipal();

        Element element = passwordRetryLimitCache.get(principal);

        if (element == null) {
            element = new Element(principal, new AtomicInteger(0));

            passwordRetryLimitCache.put(element);
        }

        AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();

        // Password Retry Limit
        if (retryCount.incrementAndGet() > 5) {
            throw new ExcessiveAttemptsException();
        }

        Boolean matches = super.doCredentialsMatch(token, info);

        if (matches) {
            passwordRetryLimitCache.remove(principal);
        }

        return matches;
    }
}
