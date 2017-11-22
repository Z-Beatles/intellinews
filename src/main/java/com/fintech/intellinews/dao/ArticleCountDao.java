package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.ArticleCountEntity;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface ArticleCountDao extends BaseDao<ArticleCountEntity> {

    /**
     * 通过文章id查询统计量
     *
     * @param articleId 文章id
     * @return 统计信息
     */
    ArticleCountEntity getByArticleId(Long articleId);

    /**
     * 更新文章浏览量
     *
     * @param id 文章id
     */
    void updateViewCountByArticleId(@Param("articleId") Long id);

    @MapKey("articleId")
    Map<Long, ArticleCountEntity> mapArticleCountByIds(List<Long> articleIds);

    /**
     * 获取文章最大浏览量
     *
     * @return 最大浏览量
     */
    Integer getMaxViewCount();
}
