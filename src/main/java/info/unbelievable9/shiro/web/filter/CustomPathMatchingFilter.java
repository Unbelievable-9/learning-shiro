package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Arrays;

/**
 * Created on : 2018/7/30
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomPathMatchingFilter extends PathMatchingFilter {

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
        log.info("Path Matched. Mapped value: " + Arrays.toString((String[]) mappedValue));

        return true;
    }
}
