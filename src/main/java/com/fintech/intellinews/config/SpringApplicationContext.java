package com.fintech.intellinews.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;

/**
 * @author wanghao
 * create 2017-12-02 23:07
 **/
@EnableTransactionManagement
@Configuration
@ComponentScan(
    basePackages = {"com.fintech.intellinews"},
    excludeFilters = {@ComponentScan.Filter(
        value = {
            EnableWebMvc.class,
            ControllerAdvice.class,
            Controller.class
        }
    )}
)
public class SpringApplicationContext {
    @Autowired
    private Environment environment;

    @Configuration
    @Profile("product")
    @PropertySource("classpath:application.product.properties")
    static class Production {
    }

    @Configuration
    @Profile("develop")
    @PropertySource("classpath:application.develop.properties")
    static class Development {
    }

    @Bean("statFilter")
    public static StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    @Bean("dataSource")
    public static DataSource dataSource(
            @Qualifier("statFilter") StatFilter statFilter){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(1);
        dataSource.setMaxActive(20);
        dataSource.setMaxWait(60000);
        dataSource.setTimeBetweenConnectErrorMillis(6000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestWhileIdle(true);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setUrl("jdbc:mysql://119.31.210.76:3306/intellinews?characterEncoding=utf8&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("YjJ2NPFnhE");
        try {
            dataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dataSource.getProxyFilters().add(statFilter);
        return dataSource;
    }

    @Bean("transactionManager")
    public static DataSourceTransactionManager transactionManager(
            @Qualifier("dataSource") DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean("sqlSessionFactory")
    public static SqlSessionFactoryBean sqlSessionFactory(
            @Qualifier("dataSource")DataSource dataSource) throws IOException {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        session.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*Mapper.xml"));
        session.setTypeAliasesPackage("com.fintech.intellinews.entity");
        session.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return session;
    }

    @Bean
    public static MapperScannerConfigurer mapperScanner(){
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setBasePackage("com.fintech.intellinews.dao");
        mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return  mapperScanner;
    }

    private String $(String name){
        return environment.getProperty(name);
    }
}
