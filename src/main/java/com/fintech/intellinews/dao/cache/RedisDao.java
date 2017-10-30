package com.fintech.intellinews.dao.cache;

import com.fintech.intellinews.base.BaseComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

/**
 * @author waynechu
 * Created 2017-10-20 12:08
 */
@Component
public class RedisDao extends BaseComponent {

    private final RedisConnectionFactory redisConnectionFactory;

    @Autowired
    public RedisDao(RedisConnectionFactory redisConnectionFactory) {
        this.redisConnectionFactory = redisConnectionFactory;
    }

    /**
     * 从redis中获取值
     *
     * @param key 键
     * @return 未查询到返回null
     */
    public byte[] get(String key) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] result = null;
        try {
            result = connection.get(key.getBytes());
        } catch (Exception e) {
            logger.warn("缓存不存在，key:{}", key);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return result;
    }

    /**
     * 保存数据
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, byte[] value) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.set(key.getBytes(), value);
            logger.info("存入缓存成功，key:{}", key);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 保存数据并设置有效期限
     *
     * @param key    键
     * @param value  值
     * @param expire 有效期限/秒
     */
    public void setEx(String key, byte[] value, long expire) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.setEx(key.getBytes(), expire, value);
            logger.warn("存入缓存成功, 有效期:{}秒, key:{}", expire, key);

        } catch (Exception e) {
            logger.warn("存入缓存失败, expire:{}, key:{}, exception:{}", expire, key, e);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }

    /**
     * 删除指定缓存
     *
     * @param key 键
     */
    public void del(String key) {
        RedisConnection connection = redisConnectionFactory.getConnection();
        try {
            connection.del(key.getBytes());
            logger.info("删除缓存成功，key:{}", key);
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
}
