package com.fintech.intellinews.base;

/**
 * @author wanghao
 * create 2017-10-30 11:39
 **/
public interface BaseDao<T> {

    /**
     * 通用添加方法
     *
     * @param t 实体
     * @return 受影响的行数
     */
    Integer insert(T t);

    /**
     * 通用删除方法
     *
     * @param t 实体
     * @return 受影响的行数
     */
    Integer delete(T t);

    /**
     * 通用更新方法
     *
     * @param t 实体
     * @return 受影响的行数
     */
    Integer update(T t);

    /**
     * 通用查询方法
     *
     * @param t 实体
     * @return 受影响的行数
     */
    T select(T t);

    /**
     * 通用统计方法
     *
     * @param t 实体
     * @return 查询的数量
     */
    Integer count(T t);

}
