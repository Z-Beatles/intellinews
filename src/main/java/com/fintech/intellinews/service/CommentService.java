package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.dao.UserLoginDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.vo.CommentVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-10 17:23
 **/
@Service
public class CommentService {

    private CommentDao commentDao;
    private UserLoginDao userLoginDao;

    @SuppressWarnings("unchecked")
    public PageInfo<CommentVO> listArticleComments(Long id, Integer pageNum, Integer pageSize){

        PageHelper.startPage(pageNum,pageSize);
        List<CommentEntity> comments = commentDao.listArticleComments(id);
        if (comments==null){
            return new PageInfo<>();
        }
        List<CommentVO> resultComments = new ArrayList<>();
        UserLoginEntity userLoginEntity;
        CommentVO commentVO;
        for (CommentEntity entity : comments){
            commentVO = new CommentVO();
            BeanUtils.copyProperties(entity,commentVO);
            userLoginEntity = userLoginDao.selectById(entity.getUserId());
            commentVO.setNickName(userLoginEntity.getNickname());
            resultComments.add(commentVO);
        }
        PageInfo pageInfo = new PageInfo(comments);
        pageInfo.setList(resultComments);
        return pageInfo;

    }


    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setUserLoginDao(UserLoginDao userLoginDao) {
        this.userLoginDao = userLoginDao;
    }
}
