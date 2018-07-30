package info.unbelievable9.shiro.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created on : 2018/7/30
 * Author     : Unbelievable9
 **/
@Slf4j
public class CustomAdviceFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("Pre handler invoked.");

        // BigDecimal infinity = BigDecimal.ONE.divide(BigDecimal.ZERO, RoundingMode.HALF_UP);

        // If return false, Filter Chain will break.
        return true;
    }

    @Override
    protected void postHandle(ServletRequest request, ServletResponse response) throws Exception {
        log.info("Custom advice filter Successfully ended.");
    }

    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        log.info("Customer advice filter finally ended.");
    }
}
