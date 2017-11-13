package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.dao.UserLoginDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.vo.CommentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-10 17:23
 **/
@Service
public class CommentService {

    private CommentDao commentDao;

    /**
     * 添加用户文章评论
     * @param userId 用户id
     * @param articleId 文章id
     * @param content 内容
     * @return 新建评论id
     */
    public Long addUserComment(Long userId,Long articleId,String content){
        CommentEntity entity = new CommentEntity();
        entity.setUserId(userId);
        entity.setArticleId(articleId);
        entity.setContent(content);
        entity.setGmtCreate(Calendar.getInstance().getTime());
        Integer count = commentDao.addUserComment(entity);
        if (count==0){
            throw new AppException(ResultEnum.FAILED.getCode(),"评论文章失败");
        }
        return entity.getId();
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
