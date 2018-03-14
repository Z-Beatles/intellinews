package com.fintech.intellinews.dao.session;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.util.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author waynechu
 * Created 2017-12-20 10:41
 */
@Component
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * session的有效时长，默认30分钟
     */
    private int defaultExpire = 30;
    private TimeUnit defaultTimeUnit = TimeUnit.MINUTES;

    /**
     * session key的前缀
     */
    private String keyPrefix = "shiro_redis_session:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        saveSession(session);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        }
        Object result = redisTemplate.opsForValue().get(getPrefixKey(sessionId));
        if (result != null) {
            return (Session) redisTemplate.opsForValue().get(getPrefixKey(sessionId));
        }
        return null;
    }

    @Override
    public void update(Session session) {
        saveSession(session);
    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or sessionId is null");
            return;
        }
        redisTemplate.delete(getPrefixKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        if (!keys.isEmpty()) {
            for (String key : keys) {
                Object result = redisTemplate.opsForValue().get(key);
                sessions.add((Session) result);
            }
        }
        return sessions;
    }

    /**
     * key: keyPrefix+sessionId
     *
     * @param sessionId sessionId
     * @return key
     */
    private String getPrefixKey(Serializable sessionId) {
        return this.keyPrefix + sessionId;
    }

    /**
     * 保存session到redis中
     *
     * @param session 当前会话
     */
    private void saveSession(Session session) {
        if (session == null || session.getId() == null) {
            logger.error("session or sessionId is null");
            return;
        }
        String key = getPrefixKey(session.getId());
        redisTemplate.opsForValue().set(key, session, defaultExpire, defaultTimeUnit);
    }

    public int getDefaultExpire() {
        return defaultExpire;
    }

    public void setDefaultExpire(int defaultExpire) {
        this.defaultExpire = defaultExpire;
    }

    public TimeUnit getDefaultTimeUnit() {
        return defaultTimeUnit;
    }

    public void setDefaultTimeUnit(TimeUnit defaultTimeUnit) {
        this.defaultTimeUnit = defaultTimeUnit;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
