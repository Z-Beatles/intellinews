package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.sun.tools.corba.se.idl.StringGen;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao extends BaseDao<CommentEntity> {

    /**
     * 获取指定文章id查询列表
     * @param id
     * @return 评论列表
     */
    List<CommentEntity> listArticleComments(@Param("articleId") Long id);

    List<CommentEntity> listUserComments(Long userId);

    Integer addUserComment(CommentEntity entity);

}