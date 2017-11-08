package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleCountEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleCountDao extends BaseDao<ArticleCountEntity> {

    /**
     * 通过文章id查询统计量
     *
     * @param articleId 文章id
     * @return 统计信息
     */
    ArticleCountEntity selectByArticleId(Long articleId);
}
