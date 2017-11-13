package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleEntity;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    /**
     * 批量查询文章
     * @param list id集合
     * @return 文章列表
     */
    List<ArticleEntity> listArticlesByIds(List<Long> list);

    /**
     * 批量查询文章
     * @param list id集合
     * @return 文章列表
     */
    @MapKey("id")
    Map<Long,ArticleEntity> mapArticlesByIds(List<Long> list);

}