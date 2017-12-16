package com.fintech.intellinews.service;

/**
 * @author wanghao
 * create 2017-11-10 17:23
 **/
public interface CommentService {

    /**
     * 添加用户文章评论
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @param content   内容
     * @return 新建评论id
     */
    Long addUserComment(Long userId, Long articleId, String content);

    /**
     * 为评论点赞/踩
     *
     * @param commentId 评论id
     * @param type      类型：type > 0 like;type < 0 dislike
     */
    void updateComment(Long commentId, int type);
}
