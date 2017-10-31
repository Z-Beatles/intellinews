package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author waynechu
 */
public interface ArticleDao extends BaseDao<ArticleEntity> {
    /**
     * 根据目录查询文章列表
     *
     * @param categoryId 目录id
     * @return 文章列表
     */
    List<ArticleEntity> getArticlesByCategoryId(@Param(value = "categoryId") Long categoryId);
}