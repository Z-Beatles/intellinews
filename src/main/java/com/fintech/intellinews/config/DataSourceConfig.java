package com.fintech.intellinews.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.fintech.intellinews.dao.cache.RedisDao;
import com.fintech.intellinews.dao.session.RedisSessionDAO;
import com.fintech.intellinews.datasource.DataSourceConnector;
import com.fintech.intellinews.datasource.DruidDataSourceFactory;
import com.fintech.intellinews.datasource.dynamic.DynamicDataSource;
import com.fintech.intellinews.properties.JedisConnectProperties;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author wanghao create 2017-12-05 14:49
 **/
public class DataSourceConfig implements EnvironmentAware {

    private Environment environment;

    @Bean("masterConnector")
    public DataSourceConnector masterConnector() {
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.master.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.master.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.master.password"));
        return dataSourceConnector;
    }

    @Bean("slave0Connector")
    public DataSourceConnector slave0Connectors() {
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.slave0.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.slave0.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.slave0.password"));
        return dataSourceConnector;
    }

    @Bean("slave1Connector")
    public DataSourceConnector slave1Connector() {
        DataSourceConnector dataSourceConnector = new DataSourceConnector();
        dataSourceConnector.setUrl(environment.getProperty("spring.datasource.split.slave1.url"));
        dataSourceConnector.setUsername(environment.getProperty("spring.datasource.split.slave1.username"));
        dataSourceConnector.setPassword(environment.getProperty("spring.datasource.split.slave1.password"));
        return dataSourceConnector;
    }

    @Bean(name = "master", initMethod = "init", destroyMethod = "close")
    public DruidDataSource master(DataSourceConnector masterConnector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUrl(masterConnector.getUrl())
                .setUsername(masterConnector.getUsername())
                .setPassword(masterConnector.getPassword())
                .build();
    }

    @Bean(name = "slave0", initMethod = "init", destroyMethod = "close")
    public DruidDataSource slave1(DataSourceConnector slave0Connector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUrl(slave0Connector.getUrl())
                .setUsername(slave0Connector.getUsername())
                .setPassword(slave0Connector.getPassword())
                .build();
    }

    @Bean(name = "slave1", initMethod = "init", destroyMethod = "close")
    public DruidDataSource slave2(DataSourceConnector slave1Connector) {
        return new DruidDataSourceFactory.DruidDataSourceBuilder()
                .setUrl(slave1Connector.getUrl())
                .setUsername(slave1Connector.getUsername())
                .setPassword(slave1Connector.getPassword())
                .build();
    }

    @Bean("dynamicDataSource")
    public DynamicDataSource dynamicDataSource(@Qualifier("master") DataSource master,
                                               @Qualifier("slave0") DataSource slave0,
                                               @Qualifier("slave1") DataSource slave1) {
        DynamicDataSource dynamic = new DynamicDataSource();
        dynamic.setReadDataSourceSelectPattern(0);
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
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        session.setDataSource(dataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        session.setMapperLocations(resolver.getResources("classpath:mybatis/mapper/*.xml"));
        session.setConfigLocation(new ClassPathResource("mybatis/mybatis-config.xml"));
        return session;
    }

    @Bean
    @DependsOn("sqlSessionFactory")
    public MapperScannerConfigurer mapperScanner() {
        MapperScannerConfigurer mapperScanner = new MapperScannerConfigurer();
        mapperScanner.setBasePackage("com.fintech.intellinews.dao");
        mapperScanner.setSqlSessionFactoryBeanName("sqlSessionFactory");
        return mapperScanner;
    }

    @Bean("redisSessionDAO")
    public RedisSessionDAO redisSessionDAO(RedisDao redisDao) {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisDao(redisDao);
        // session有效期30分钟
        redisSessionDAO.setExpire(1800);
        redisSessionDAO.setKeyPrefix("shiro_redis_session:");
        return redisSessionDAO;
    }

    @Bean("jedisPoolConfig")
    public JedisPoolConfig jedisPoolConfig(JedisConnectProperties jedisConnectProperties) {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(jedisConnectProperties.getMaxTotal());
        config.setMaxIdle(jedisConnectProperties.getMaxIdle());
        config.setMaxWaitMillis(jedisConnectProperties.getMaxWaitMillis());
        config.setTestOnBorrow(jedisConnectProperties.getTestOnBorrow());
        return config;
    }

    @Bean("jedisPool")
    public JedisPool jedisPool(JedisPoolConfig jedisPoolConfig, JedisConnectProperties jedisConnectProperties) {
        String hostName = jedisConnectProperties.getHostName();
        int port = jedisConnectProperties.getPort();
        int timeout = jedisConnectProperties.getTimeout();
        String password = jedisConnectProperties.getPassword();
        return new JedisPool(jedisPoolConfig, hostName, port, timeout, password);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
