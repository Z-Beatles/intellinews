package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserArticleDao extends BaseDao<UserArticleEntity> {

    List<UserArticleEntity> getUserCollectArticles(Long userId);

    Integer checkUserArticle(Map<String,Long> param);

    Integer deleteCollectArticle(UserArticleEntity entity);

    UserArticleEntity getUserArticle(UserArticleEntity userArticleEntity);

}