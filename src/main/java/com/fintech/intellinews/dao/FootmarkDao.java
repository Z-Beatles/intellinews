package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.entity.FootmarkEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FootmarkDao extends BaseDao<FootmarkEntity> {
    List<FootmarkEntity> getUserFootmarks(Long userId);
}