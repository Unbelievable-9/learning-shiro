package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created on : 2018/7/30
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomFormLoginFilter extends PathMatchingFilter {

    private static final String LOGIN_URL = "/customLogin.jsp";
    private static final String LOGIN_SUCCESS_URL = "/";

    private boolean isLoginRequest(HttpServletRequest request) {
        return pathsMatch(LOGIN_URL, request);
    }

    private boolean login(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            request.setAttribute("shiroLoginFailure", e.getClass());

            return false;
        }

        return true;
    }

    private void saveRequestAndRedirectToLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebUtils.saveRequest(request);
        WebUtils.issueRedirect(request, response, LOGIN_URL);
    }

    private boolean redirectToLoginSuccess(HttpServletRequest request, HttpServletResponse response) throws IOException {
        WebUtils.redirectToSavedRequest(request, response, LOGIN_SUCCESS_URL);

        return false;
    }

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        if (SecurityUtils.getSubject().isAuthenticated()) {
            return true;
        }

        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;

        if (isLoginRequest(servletRequest)) {
            if (servletRequest.getMethod().equalsIgnoreCase("post")) {
                boolean loginSuccess = login(servletRequest);

                if (loginSuccess) {
                    return redirectToLoginSuccess(servletRequest, servletResponse);
                }
            }

            return true;
        } else {
            saveRequestAndRedirectToLogin(servletRequest, servletResponse);

            return true;
        }
    }
}
