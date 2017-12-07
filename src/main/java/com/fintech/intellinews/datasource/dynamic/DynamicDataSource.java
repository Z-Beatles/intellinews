package com.fintech.intellinews.datasource.dynamic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author wanghao
 * create 2017-12-05 12:57
 **/
public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 写数据源
     */
    private Object master;
    /**
     * 多个读数据源
     */
    private List<Object> slaves;
    /**
     * 多数据源个数
     */
    private int readDataSourceSize;
    /**
     * 获取多数据源方式，0：随机，1：轮询
     */
    private int readDataSourcePollPattern = 1;
    private AtomicLong counter = new AtomicLong(0);
    private static final Long MAX_POOL = Long.MAX_VALUE;
    /**
     * 线程锁
     */
    private final Lock lock = new ReentrantLock();

    /**
     * 在bean加载并且设置完属性之后执行
     */
    @Override
    public void afterPropertiesSet() {
        // 写数据源
        if (this.master == null) {
            throw new IllegalArgumentException("master is required");
        }
        setDefaultTargetDataSource(master);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceHolder.DATA_SOURCE_MASTER, master);
        if (slaves.isEmpty()) {
            readDataSourceSize = 0;
            logger.warn("slaves is empty");
        } else {
            for (int i = 0; i < slaves.size(); i++) {
                targetDataSources.put(DynamicDataSourceHolder.DATA_SOURCE_SALVE + i, slaves.get(i));
            }
            readDataSourceSize = slaves.size();
        }
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }

    /**
     * 决定数据源key
     *
     * @return 数据源key
     */
    @Override
    protected Object determineCurrentLookupKey() {
        String dynamicKey = DynamicDataSourceHolder.getDataSourceType();
        if (DynamicDataSourceHolder.DATA_SOURCE_MASTER.equals(dynamicKey) || readDataSourceSize <= 0) {
            logger.info("》》》》》》》》》》》》》》》》》》" + dynamicKey + "《《《《《《《《《《《《《《《《《");
            return DynamicDataSourceHolder.DATA_SOURCE_MASTER;
        }
        int index;
        // 可以添加负载均衡算法
        if (readDataSourcePollPattern == 1) {
            long currValue = counter.incrementAndGet();
            if ((currValue + 1) >= MAX_POOL) {
                try {
                    lock.lock();
                    if ((currValue + 1) >= MAX_POOL) {
                        counter.set(0);
                    }
                } finally {
                    lock.unlock();
                }
            }
            index = (int) (currValue % readDataSourceSize);
        } else if (readDataSourcePollPattern == 0) {
            //随机方式
            index = ThreadLocalRandom.current().nextInt(0, readDataSourceSize);
        } else {
            return DynamicDataSourceHolder.DATA_SOURCE_MASTER;
        }
        logger.info("》》》》》》》》》》》》》》》》》》" + (dynamicKey + index) + "《《《《《《《《《《《《《《《《《");
        return dynamicKey + index;
    }

    public void setMaster(Object master) {
        this.master = master;
    }

    public void setSlaves(List<Object> slaves) {
        this.slaves = slaves;
    }

    public void setReadDataSourcePollPattern(int readDataSourcePollPattern) {
        this.readDataSourcePollPattern = readDataSourcePollPattern;
    }
}
