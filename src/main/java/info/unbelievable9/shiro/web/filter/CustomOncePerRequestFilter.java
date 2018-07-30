package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created on : 2018/7/30
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomOncePerRequestFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws ServletException, IOException {
        log.info("Only invoke once per request.");

        filterChain.doFilter(servletRequest, servletResponse);
    }
}
