package com.fintech.intellinews.base;

import java.util.List;

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
     * @return 查询结果
     */
    List<T> list(T t);

    /**
     * 根据id查询
     *
     * @param id id
     * @return 查询结果
     */
    T getById(Long id);

    /**
     * 通用统计方法
     *
     * @param t 实体
     * @return 查询的数量
     */
    Integer count(T t);

}
