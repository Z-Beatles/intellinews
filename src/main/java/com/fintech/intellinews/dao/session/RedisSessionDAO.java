package com.fintech.intellinews.dao.session;

import com.fintech.intellinews.dao.cache.RedisDao;
import com.fintech.intellinews.util.SerializableUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author waynechu
 * Created 2017-12-20 10:41
 */
public class RedisSessionDAO extends AbstractSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);

    private RedisDao redisDao;

    /**
     * session的有效时长，默认30分钟，单位毫秒
     */
    private int expire = 1800000;

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
        byte[] value = redisDao.get(getPrefixKey(sessionId));
        if (value != null) {
            return SerializableUtils.deserialize(value);
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
        redisDao.del(getPrefixKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
        Set<Session> sessions = new HashSet<>();
        Set<byte[]> keys = redisDao.keys(keyPrefix + "*");
        if (keys != null && !keys.isEmpty()) {
            byte[] value;
            Session session;
            for (byte[] key : keys) {
                value = redisDao.get(getPrefixKey(Arrays.toString(key)));
                session = SerializableUtils.deserialize(value);
                sessions.add(session);
            }
        }
        return sessions;
    }

    /**
     * 将前缀+sessionId 作为key
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
            logger.error("session or session id is null");
            return;
        }
        String key = getPrefixKey(session.getId());
        byte[] value = SerializableUtils.serialize(session);
        session.setTimeout(expire);
        redisDao.setEx(key, value, expire);
    }

    public RedisDao getRedisDao() {
        return redisDao;
    }

    public void setRedisDao(RedisDao redisDao) {
        this.redisDao = redisDao;
    }

    public int getExpire() {
        return expire;
    }

    public void setExpire(int expire) {
        this.expire = expire;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
