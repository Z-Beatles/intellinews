package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dao.UserArticleDao;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.UserArticleEntity;
import com.fintech.intellinews.vo.UserCollectVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-11 20:34
 **/
@Service
public class UserArticleService {

    private UserArticleDao userArticleDao;

    private ArticleDao articleDao;

    /**
     * 添加用户收藏文章
     * @param userId 用户id
     * @param articleId 文章id
     * @return 影响行数
     */
    public Integer insertUserArticle(Long userId,Long articleId){
        UserArticleEntity userArticle = new UserArticleEntity();
        userArticle.setUserId(userId);
        userArticle.setResourceId(articleId);
        userArticle.setCollectTime(Calendar.getInstance().getTime());
        userArticle.setGmtCreate(Calendar.getInstance().getTime());
        return userArticleDao.insert(userArticle);
    }

    /**
     * 获取用户收藏文章
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @SuppressWarnings("unchecked")
    public PageInfo<UserCollectVO> getUserCollectArticles(Long userId, Integer pageNum, Integer pageSize){

        List<UserArticleEntity> userArticles = userArticleDao.getUserCollectArticles(userId);
        List<Long> listId = new ArrayList<>();
        for (UserArticleEntity entity : userArticles){
            listId.add(entity.getId());
        }
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleEntity> articles = articleDao.listArticlesByIds(listId);
        List<UserCollectVO> resultList = new ArrayList<>();
        UserCollectVO articleVO ;
        for (ArticleEntity entity : articles){
            articleVO = new UserCollectVO();
            articleVO.setId(entity.getId());
            articleVO.setTitle(entity.getTitle());
            articleVO.setType("文章");
        }
        PageInfo pageInfo = new PageInfo(articles);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    @Autowired
    public void setUserArticleDao(UserArticleDao userArticleDao) {
        this.userArticleDao = userArticleDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
