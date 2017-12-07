package com.fintech.intellinews.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.fintech.intellinews.datasource.DruidDataSourceFactory;
import com.fintech.intellinews.datasource.dynamic.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author wanghao
 * create 2017-12-05 14:49
 **/
@Configuration
public class DynamicDataSourceConfig {

    @Bean("statFilter")
    public StatFilter statFilter(){
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    @Bean("master")
    public DruidDataSource master(@Qualifier("statFilter")StatFilter statFilter) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUsername("root")
                .setPassword("AE6eiO3r.E1")
                .setUrl("jdbc:mysql://119.31.210.76:3316/intellinews?characterEncoding=utf8&useSSL=false")
                .setFilters("mergeStat")
                .setStatFilter(statFilter)
                .build();
    }

    @Bean("slave1")
    public DruidDataSource slave1(@Qualifier("statFilter")StatFilter statFilter) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUsername("root")
                .setPassword("AE6eiO3r.E1")
                .setUrl("jdbc:mysql://119.31.210.76:3317/intellinews?characterEncoding=utf8&useSSL=false")
                .setFilters("mergeStat")
                .setStatFilter(statFilter)
                .build();
    }

    @Bean("slave2")
    public DruidDataSource slave2(@Qualifier("statFilter")StatFilter statFilter) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUsername("root")
                .setPassword("AE6eiO3r.E1")
                .setUrl("jdbc:mysql://119.31.210.76:3318/intellinews?characterEncoding=utf8&useSSL=false")
                .setFilters("mergeStat")
                .setStatFilter(statFilter)
                .build();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(
            @Qualifier("master") DruidDataSource master,
            @Qualifier("slave1") DataSource slave1,
            @Qualifier("slave2") DataSource slave2) {
        DynamicDataSource dynamic = new DynamicDataSource();
        dynamic.setMaster(master);
        dynamic.setSlaves(Arrays.asList(slave1, slave2));
        return dynamic;
    }

    @Bean("dataSource")
    public LazyConnectionDataSourceProxy dataSource(DynamicDataSource dynamicDataSource) {
        LazyConnectionDataSourceProxy dataSource = new LazyConnectionDataSourceProxy();
        dataSource.setTargetDataSource(dynamicDataSource);
        return dataSource;
    }

    @Bean("transactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
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
