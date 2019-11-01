package cn.ann;

import cn.ann.config.JdbcConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-1 15:21
 */
@Configuration
@ComponentScan("cn.ann")
@Import(JdbcConfig.class)
public class SpringConfiguration {
}
