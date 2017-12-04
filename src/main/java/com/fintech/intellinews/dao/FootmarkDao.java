package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.FootmarkEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootmarkDao extends BaseDao<FootmarkEntity> {
    List<FootmarkEntity> getUserFootmarks(Long userId);

    /**
     * 删除用户浏览足迹
     *
     * @param footmarkId 足迹id
     * @return 受影响的行数
     */
    Integer deleteUserFootmarkById(@Param("footmarkId") Long footmarkId);
}