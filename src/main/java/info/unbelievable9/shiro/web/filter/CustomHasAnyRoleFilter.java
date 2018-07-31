package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.StringUtils;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

/**
 * Created on : 2018/7/31
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomHasAnyRoleFilter extends AccessControlFilter {

    private static final String ACCESS_DENIED_URL = "/accessDenied";
    private static final String LOGIN_URL = "/customLogin";

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        String[] requiredRoles = (String[])o;

        if (requiredRoles == null) {
            log.info("No required role, access allowed as default.");

            return true;
        } else {
            for (String role : requiredRoles) {
                if (getSubject(servletRequest, servletResponse).hasRole(role)) {
                    return true;
                }
            }

            return false;
        }
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        Subject subject = getSubject(servletRequest, servletResponse);

        if (subject.isAuthenticated()) {
            if (StringUtils.hasText(ACCESS_DENIED_URL)) {
                WebUtils.issueRedirect(servletRequest, servletResponse, ACCESS_DENIED_URL);
            } else {
                // Error 401
                WebUtils.toHttp(servletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED);
            }
        } else {
            saveRequest(servletRequest);

            WebUtils.issueRedirect(servletRequest, servletResponse, LOGIN_URL);
        }

        return false;
    }
}
