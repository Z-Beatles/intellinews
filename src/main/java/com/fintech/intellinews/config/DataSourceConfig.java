package com.fintech.intellinews.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * create 2017-12-05 14:49
 **/
@Configuration
public class DataSourceConfig {

    @Bean("abstractDataSource")
    public static DruidDataSource abstractDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(1);
        dataSource.setMaxWait(60000);
        dataSource.setMaxActive(20);
        dataSource.setTimeBetweenConnectErrorMillis(6000);
        dataSource.setMinEvictableIdleTimeMillis(300000);
        dataSource.setValidationQuery("SELECT 'x'");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setPoolPreparedStatements(true);
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(20);
        dataSource.setPoolPreparedStatements(true);
        try {
            dataSource.setFilters("mergeStat");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Bean("dataSourceWrite")
    public DataSource dataSourceWrite(@Qualifier("abstractDataSource") DruidDataSource druidDataSource) {
        DruidDataSource write = new DruidDataSource();
        BeanUtils.copyProperties(write, druidDataSource);
        write.setUrl("jdbc:mysql://119.31.210.76:3306/intellinews?characterEncoding=utf8&useSSL=false");
        write.setPassword("root");
        write.setUsername("YjJ2NPFnhE");
        write.setMaxActive(60000);
        write.setInitialSize(2);
        return write;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("abstractDataSource") DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean("sqlSessionFactory")
    public static SqlSessionFactoryBean sqlSessionFactory(
            @Qualifier("dataSourceWrite") DataSource dataSourceWrite) throws IOException {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        session.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        session.setDataSource(dataSourceWrite);
        session.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return session;
    }

    @Bean
    public static MapperScannerConfigurer mapperScanner() {
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setBasePackage("com.fintech.intellinews.dao");
        mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScanner;
    }
}
