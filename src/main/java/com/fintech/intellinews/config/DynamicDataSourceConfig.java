package com.fintech.intellinews.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fintech.intellinews.base.dynamic.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author wanghao
 * create 2017-12-05 14:49
 **/
@Configuration
public class DynamicDataSourceConfig {

    @Bean("abstractDataSource")
    public static DruidDataSource abstractDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setInitialSize(2);
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

    @Bean("master")
    public DruidDataSource master(@Qualifier("abstractDataSource") DruidDataSource druidDataSource) {
        DruidDataSource master = new DruidDataSource();
        BeanUtils.copyProperties(master, druidDataSource);
        master.setUrl("jdbc:mysql://119.31.210.76:3316/intellinews?characterEncoding=utf8&useSSL=false");
        master.setUsername("root");
        master.setPassword("WRy93Ee4sGI");
        return master;
    }

    @Bean("slave1")
    public DruidDataSource slave1(@Qualifier("abstractDataSource") DruidDataSource druidDataSource) {
        DruidDataSource slave1 = new DruidDataSource();
        BeanUtils.copyProperties(slave1, druidDataSource);
        slave1.setUrl("jdbc:mysql://119.31.210.76:3316/intellinews?characterEncoding=utf8&useSSL=false");
        slave1.setUsername("root");
        slave1.setPassword("WRy93Ee4sGI");
        return slave1;
    }

    @Bean("slave2")
    public DruidDataSource slave2(@Qualifier("abstractDataSource") DruidDataSource druidDataSource) {
        DruidDataSource slave2 = new DruidDataSource();
        BeanUtils.copyProperties(slave2, druidDataSource);
        slave2.setUrl("jdbc:mysql://119.31.210.76:3316/intellinews?characterEncoding=utf8&useSSL=false");
        slave2.setUsername("root");
        slave2.setPassword("WRy93Ee4sGI");
        return slave2;
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(
            @Qualifier("master") DruidDataSource master,
            @Qualifier("slave1") DataSource slave1,
            @Qualifier("slave2") DataSource slave2) {
        DynamicDataSource dynamic = new DynamicDataSource();
        dynamic.setMaster(master);
        dynamic.setSlaves(Arrays.asList(slave1,slave2));
        return dynamic;
    }

    @Bean("dataSource")
    public LazyConnectionDataSourceProxy dataSource(DynamicDataSource dynamicDataSource) {
        LazyConnectionDataSourceProxy dataSource = new LazyConnectionDataSourceProxy();
        dataSource.setTargetDataSource(dynamicDataSource);
        return dataSource;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("abstractDataSource") DataSource dataSource){
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    @Bean("sqlSessionFactory")
    public static SqlSessionFactoryBean sqlSessionFactory(
            DataSource dataSource) throws IOException {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        session.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
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
