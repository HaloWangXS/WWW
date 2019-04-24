package com.spring.datasource.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    DataSourceTransactionManagerAutoConfiguration.class
})
@EnableTransactionManagement
public class DataSourceConfig {
    @Bean(name = "firstDataSourceProperties")
    @ConfigurationProperties("spring.datasource.first")
    public DataSourceProperties firstDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "firstDataSource")
    public DataSource firstDataSource() {
        DataSourceProperties dataSourceProperties = firstDataSourceProperties();
        return dataSourceProperties.initializeDataSourceBuilder().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }
    @Bean(name = "firstTxManager")
    public PlatformTransactionManager firstTxManager() {
        return new DataSourceTransactionManager(firstDataSource());
    }

    /**
     * ---------------------------------
     */
    @Bean(name = "secondDataSourceProperties")
    @ConfigurationProperties("spring.datasource.second")
    public DataSourceProperties secondDataSourceProperties() {
        return new DataSourceProperties();
    }
    @Bean(name = "secondDataSource")
    @Primary
    public DataSource secondDataSource() {
        DataSourceProperties dataSourceProperties = secondDataSourceProperties();
        return dataSourceProperties.initializeDataSourceBuilder().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }
    @Bean(name = "secondTxManager")
    @Primary
    public PlatformTransactionManager secondTxManager() {
        return new DataSourceTransactionManager(secondDataSource());
    }
}
