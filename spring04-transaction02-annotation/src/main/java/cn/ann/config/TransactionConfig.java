package cn.ann.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 11:54
 */
@Configuration
@EnableTransactionManagement
public class TransactionConfig {
    @Bean("transactionManager")
    public DataSourceTransactionManager createTransactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }



}
