package com.fintech.intellinews.config;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author wanghao
 * create 2017-12-02 23:31
 **/
public class RedisConfig {

    @Bean("poolConfig")
    public JedisPoolConfig initPool() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMinIdle(1);
        poolConfig.setMaxIdle(8);
        poolConfig.setMaxWaitMillis(-1);
        return poolConfig;
    }

    @Bean("redisConnectionFactory")
    public JedisConnectionFactory initConnectionFactory(JedisPoolConfig poolConfig) {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setPoolConfig(poolConfig);
        connectionFactory.setHostName("127.0.0.1");
        connectionFactory.setPort(6379);
        connectionFactory.setPassword("");
        connectionFactory.setTimeout(15000);
        connectionFactory.setUsePool(true);
        return connectionFactory;
    }


}
