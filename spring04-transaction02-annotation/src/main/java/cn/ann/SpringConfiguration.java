package cn.ann;

import cn.ann.config.JdbcConfig;
import cn.ann.config.TransactionConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 11:54
 */
@Configuration
@ComponentScan("cn.ann")
@Import({JdbcConfig.class, TransactionConfig.class})
public class SpringConfiguration {
}
