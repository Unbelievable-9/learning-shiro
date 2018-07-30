package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created on : 2018/7/30
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomAccessControlFilter extends AccessControlFilter {

    @Override
    protected boolean isAccessAllowed(ServletRequest servletRequest, ServletResponse servletResponse, Object o) throws Exception {
        log.info("Access denied.");

        return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        servletRequest.getRequestDispatcher("/WEB-INF/jsp/accessDenied.jsp").forward(servletRequest, servletResponse);

        return false;
    }
}
