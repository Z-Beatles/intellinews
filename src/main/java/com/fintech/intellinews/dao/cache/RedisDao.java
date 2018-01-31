package com.fintech.intellinews.dao.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @author waynechu
 * Created 2017-12-20 10:10
 */
@Component
public class RedisDao {

    private static final Logger logger = LoggerFactory.getLogger(RedisDao.class);

    @Resource
    private JedisPool jedisPool;

    /**
     * 从redis中获取值
     *
     * @param key 键
     * @return 未查询到返回null
     */
    public byte[] get(String key) {
        Jedis resource = jedisPool.getResource();
        byte[] result = null;
        try {
            result = resource.get(key.getBytes());
        } catch (Exception e) {
            logger.warn("缓存不存在，key:{}", key);
        } finally {
            if (resource != null) {
                resource.close();
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
        Jedis resource = jedisPool.getResource();
        try {
            resource.set(key.getBytes(), value);
            logger.debug("存入缓存成功，key:{}", key);
        } finally {
            if (resource != null) {
                resource.close();
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
    public void setEx(String key, byte[] value, int expire) {
        Jedis resource = jedisPool.getResource();
        try {
            resource.setex(key.getBytes(), expire, value);
            logger.debug("存入缓存成功, 有效期:{}秒, key:{}", expire, key);
        } catch (Exception e) {
            logger.warn("存入缓存失败, key:{}, exception:{}", expire, key, e);
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * 删除指定缓存
     *
     * @param key 键
     */
    public void del(String key) {
        Jedis resource = jedisPool.getResource();
        try {
            resource.del(key.getBytes());
            logger.debug("删除缓存成功，key:{}", key);
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
    }

    /**
     * 根据正则获取所有符合条件的值
     *
     * @param pattern 表达式
     * @return 符合条件的值
     */
    public Set<byte[]> keys(String pattern) {
        Jedis resource = jedisPool.getResource();
        Set<byte[]> keys;
        try {
            keys = resource.keys(pattern.getBytes());
        } finally {
            if (resource != null) {
                resource.close();
            }
        }
        return keys;
    }
}
