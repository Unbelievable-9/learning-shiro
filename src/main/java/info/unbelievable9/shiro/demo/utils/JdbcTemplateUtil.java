package info.unbelievable9.shiro.demo.utils;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Created on : 2018/7/11
 * Author     : Unbelievable9
 **/
public class JdbcTemplateUtil {

    private static JdbcTemplate jdbcTemplate;

    private static JdbcTemplate createJdbcTemplate() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://127.0.0.1:3306/shiro?useUnicode=true&characterEncoding=UTF-8");
        dataSource.setUsername("demo");
        dataSource.setPassword("demo");

        return new JdbcTemplate(dataSource);
    }

    public static JdbcTemplate jdbcTemplate() {
        if (jdbcTemplate == null) jdbcTemplate = createJdbcTemplate();

        return jdbcTemplate;
    }
}
