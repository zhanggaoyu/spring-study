package cn.ann.config;

import cn.ann.dao.AccountDao;
import cn.ann.dao.impl.AccountDaoImpl;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-3 11:54
 */
@Configuration
@PropertySource("classpath:database.properties")
public class JdbcConfig {
    @Value("${mysql.driver}")
    private String driver;
    @Value("${mysql.url}")
    private String url;
    @Value("${mysql.username}")
    private String username;
    @Value("${mysql.password}")
    private String password;

    @Bean("dataSource")
    public DataSource createDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver);
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean("accountDao")
    public AccountDao createAccountDao(DataSource dataSource) {
        AccountDaoImpl accountDao = new AccountDaoImpl();
        accountDao.setDataSource(dataSource);
        return accountDao;
    }

    @Bean("runner")
    public QueryRunner createQueryRunner(DataSource dataSource) {
        QueryRunner runner = new QueryRunner(dataSource);
        return runner;
    }

}
