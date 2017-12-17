package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.FootmarkEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserFootmarkDao {

    /**
     * 获取指定用户的流览足迹
     *
     * @param userId 用户id
     * @return 浏览足迹列表
     */
    List<FootmarkEntity> getUserFootmarks(Long userId);

    /**
     * 删除指定浏览足迹
     *
     * @param footmarkId 足迹id
     * @return 受影响的行数
     */
    Integer deleteUserFootmarkById(@Param("footmarkId") Long footmarkId);

    /**
     * 添加用户浏览足迹
     * @param entity 足迹对象
     */
    void insertUserFootmark(FootmarkEntity entity);
}