package com.fintech.intellinews.service;

import com.fintech.intellinews.entity.UserArticleEntity;
import com.fintech.intellinews.vo.UserCollectVO;
import com.github.pagehelper.PageInfo;

/**
 * @author wanghao
 * create 2017-11-11 20:34
 **/
public interface UserArticleService {

    /**
     * 添加用户收藏文章
     *
     * @param userId     用户id
     * @param resourceId 文章id
     * @return 影响行数
     */
    Integer insertUserArticle(Long userId, Long resourceId);

    /**
     * 取消收藏文章
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @return 影响行数
     */
    Integer deleteUserArticle(Long userId, Long articleId);

    /**
     * 获取用户的收藏的指定文章
     *
     * @param userId     用户id
     * @param resourceId 文章id
     * @return 收藏的文章对象
     */
    UserArticleEntity getUserArticle(Long userId, Long resourceId);

    /**
     * 获取用户收藏的文章
     *
     * @param userId   用户id
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 用户收藏分页列表
     */
    PageInfo<UserCollectVO> getUserCollectArticles(Long userId, Integer pageNum, Integer pageSize);
}
