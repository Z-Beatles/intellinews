package com.fintech.intellinews.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fintech.intellinews.datasource.DataSourceConnector;
import com.fintech.intellinews.datasource.DruidDataSourceFactory;
import com.fintech.intellinews.datasource.dynamic.DynamicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author wanghao create 2017-12-05 14:49
 **/
@Configuration
public class DataSourceConfig implements EnvironmentAware {

    private Environment environment;

    @Bean("masterConnector")
    public DataSourceConnector masterConnector(){
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.master.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.master.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.master.password"));
        return dataSourceConnector;
    }

    @Bean("slave0Connector")
    public DataSourceConnector slave0Connectors(){
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.slave0.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.slave0.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.slave0.password"));
        return dataSourceConnector;
    }

    @Bean("slave1Connector")
    public DataSourceConnector slave1Connector(){
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.slave1.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.slave1.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.slave1.password"));
        return dataSourceConnector;
    }

    @Bean("master")
    public DruidDataSource master(DataSourceConnector masterConnector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUrl(masterConnector.getUrl())
                .setUsername(masterConnector.getUsername())
                .setPassword(masterConnector.getPassword())
                .build();
    }

    @Bean("slave0")
    public DruidDataSource slave1(DataSourceConnector slave0Connector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUrl(slave0Connector.getUrl())
                .setUsername(slave0Connector.getUsername())
                .setPassword(slave0Connector.getPassword())
                .build();
    }

    @Bean("slave1")
    public DruidDataSource slave2(DataSourceConnector slave1Connector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder().setUrl(slave1Connector.getUrl())
                .setUsername(slave1Connector.getUsername())
                .setPassword(slave1Connector.getPassword())
                .build();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("master") DruidDataSource master,
            @Qualifier("slave0") DataSource slave0, @Qualifier("slave1") DataSource slave1) {
        DynamicDataSource dynamic = new DynamicDataSource();
        dynamic.setReadDataSourcePollPattern(1);
        dynamic.setMaster(master);
        dynamic.setSlaves(Arrays.asList(slave0, slave1));
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
    public static SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        session.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        session.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return session;
    }

    @Bean
    @DependsOn("sqlSessionFactory")
    public static MapperScannerConfigurer mapperScanner() {
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setBasePackage("com.fintech.intellinews.dao");
        mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScanner;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
