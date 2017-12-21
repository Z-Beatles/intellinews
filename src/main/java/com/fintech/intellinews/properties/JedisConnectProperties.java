package com.fintech.intellinews.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author waynechu
 * Created 2017-12-20 13:12
 */
@Component("jedisConnectProperties")
public class JedisConnectProperties {

    @Value("${spring.redis.hostName}")
    private String hostName;

    @Value("${spring.redis.port}")
    private int port;

    @Value("${spring.redis.password}")
    private String password;

    @Value("${spring.redis.timeout}")
    private int timeout;

    @Value("${spring.redis.usePool}")
    private boolean usePool;

    /** 一个pool所能分配的最大的连接数目 */
    @Value("${spring.redis.pool.max-total}")
    private int maxTotal;

    /** 最大空闲连接数 */
    @Value("${spring.redis.pool.max-idle}")
    private int maxIdle;

    /** 超时时间，如果超过等待时间抛出JedisConnectionException */
    @Value("${spring.redis.pool.max-wait-millis}")
    private int maxWaitMillis;

    /** 获取连接的时候检查有效性，确保连接池内的连接都可用 */
    @Value("${spring.redis.pool.test-on-borrow}")
    private boolean testOnBorrow;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public boolean getUsePool() {
        return usePool;
    }

    public void setUsePool(boolean usePool) {
        this.usePool = usePool;
    }

    public int getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(int maxTotal) {
        this.maxTotal = maxTotal;
    }

    public int getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(int maxIdle) {
        this.maxIdle = maxIdle;
    }

    public int getMaxWaitMillis() {
        return maxWaitMillis;
    }

    public void setMaxWaitMillis(int maxWaitMillis) {
        this.maxWaitMillis = maxWaitMillis;
    }

    public boolean getTestOnBorrow() {
        return testOnBorrow;
    }

    public void setTestOnBorrow(boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }
}
