package com.fintech.intellinews.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.intellinews.properties.RedisConnectProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author waynechu
 * Created 2018-03-14 09:12
 */
public class RedisConfig {

    @Bean("RedisCacheManager")
    public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        return RedisCacheManager.create(connectionFactory);
    }

    @Bean("redisConnectionFactory")
    public RedisConnectionFactory redisConnectionFactory(RedisConnectProperties redisConnectProperties) {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisConnectProperties.getHostName(), redisConnectProperties.getPort());
        redisStandaloneConfiguration.setPassword(RedisPassword.of(redisConnectProperties.getPassword()));
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisSerializer<String> stringSerializer = new StringRedisSerializer();
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
