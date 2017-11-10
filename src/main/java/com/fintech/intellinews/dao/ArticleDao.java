package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleDao extends BaseDao<ArticleEntity> {

    /**
     * 根据关键搜索文章
     * @param keyword 关键字
     * @return 文章列表
     */
    List<ArticleEntity> listArticleByKeyword(String keyword);

    /**
     * 获取最新文章列表
     *
     * @return 文章列表
     */
    List<ArticleEntity> listLatestArticles();

}