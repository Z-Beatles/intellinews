package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserArticleDao extends BaseDao<UserArticleEntity> {

    List<UserArticleEntity> getUserCollectArticles(Long userId);

}