package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.UserArticleEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserArticleDao {

    /**
     * 根据用户id获取用户收藏的文章列表
     *
     * @param userId 用户id
     * @return 用户收藏的文章列表
     */
    List<UserArticleEntity> getUserCollectArticles(Long userId);

    /**
     * 检查用户是否已收藏该文章
     *
     * @param userId     用户id
     * @param resourceId 资源id
     * @return 是否收藏
     */
    Integer checkUserArticle(@Param("userId") Long userId, @Param("resourceId") Long resourceId);

    /**
     * 用户取消收藏文章
     *
     * @param entity 收藏对象
     * @return 受影响的行数
     */
    Integer deleteCollectArticle(UserArticleEntity entity);

    /**
     * 获取用户收藏的文章
     *
     * @param userArticleEntity 收藏对象
     * @return 收藏对象
     */
    UserArticleEntity getUserArticle(UserArticleEntity userArticleEntity);

    /**
     * 用户收藏文章
     *
     * @param userArticleEntity 收藏对象
     * @return 受影响的行数
     */
    Integer insertUserArticle(UserArticleEntity userArticleEntity);
}