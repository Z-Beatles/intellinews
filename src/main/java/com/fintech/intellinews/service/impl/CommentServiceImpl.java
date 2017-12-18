package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * @author wanghao
 * create 2017-11-10 17:23
 **/
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentDao commentDao;

    /**
     * 添加用户文章评论
     *
     * @param userId    用户id
     * @param articleId 文章id
     * @param content   内容
     * @return 新建评论id
     */
    @Override
    public Long addUserComment(Long userId, Long articleId, String content) {
        CommentEntity entity = new CommentEntity();
        entity.setUserId(userId);
        entity.setArticleId(articleId);
        entity.setContent(content);
        entity.setGmtCreate(Calendar.getInstance().getTime());
        Integer count = commentDao.addUserComment(entity);
        if (count == 0) {
            throw new AppException(ResultEnum.COMMENT_ARTICLE_FAILED_ERROR);
        }
        return entity.getId();
    }

    /**
     * 为评论点赞/踩
     *
     * @param commentId 评论id
     * @param type      类型：type > 0 like;type < 0 dislike
     */
    @Override
    public void updateComment(Long commentId, int type) {
        Integer flag;
        if (type > 0) {
            flag = commentDao.updateWithLike(commentId);
        } else {
            flag = commentDao.updateWithDislike(commentId);
        }
        if (flag == 0) {
            throw new AppException(ResultEnum.COMMENT_NOT_EXIST_ERROR);
        }
    }
}
