package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dao.UserArticleDao;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.UserArticleEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.vo.UserCollectVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
     *
     * @param userId     用户id
     * @param resourceId 文章id
     * @return 影响行数
     */
    public Integer insertUserArticle(Long userId, Long resourceId) {
        Map<String, Long> param = new HashMap<>();
        param.put("userId", userId);
        param.put("resourceId", resourceId);
        Integer count = userArticleDao.checkUserArticle(param);
        if (count > 0) {
            throw new AppException(ResultEnum.COLLECT_ARTICLE_FAILED_ERROR);
        }
        UserArticleEntity insertArticle = new UserArticleEntity();
        insertArticle.setUserId(userId);
        insertArticle.setResourceId(resourceId);
        insertArticle.setResourceType("文章");
        insertArticle.setGmtCreate(Calendar.getInstance().getTime());
        return userArticleDao.insert(insertArticle);
    }

    /**
     * 取消收藏文章
     *
     * @return 影响行数
     */
    public Integer deleteUserArticle(Long userId, Long articleId) {
        UserArticleEntity article = new UserArticleEntity();
        article.setUserId(userId);
        article.setResourceId(articleId);
        return userArticleDao.deleteCollectArticle(article);
    }


    /**
     * 获取用户的收藏文章
     *
     * @param userId     用户id
     * @param resourceId 文章id
     * @return 收藏文章对象
     */
    public UserArticleEntity getUserArticle(Long userId, Long resourceId) {
        UserArticleEntity userArticleEntity = new UserArticleEntity();
        userArticleEntity.setUserId(userId);
        userArticleEntity.setResourceId(resourceId);
        UserArticleEntity userArticle = userArticleDao.getUserArticle(userArticleEntity);
        if (userArticle == null) {
            throw new AppException(ResultEnum.ARTICLE_NOT_COLLECT_ERROR);
        }
        return userArticle;
    }

    /**
     * 获取用户收藏的文章
     *
     * @param userId   用户id
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 用户收藏分页列表
     */
    @SuppressWarnings("unchecked")
    public PageInfo<UserCollectVO> getUserCollectArticles(Long userId, Integer pageNum, Integer pageSize) {
        List<UserArticleEntity> userArticles = userArticleDao.getUserCollectArticles(userId);
        List<Long> listId = new ArrayList<>();
        for (UserArticleEntity entity : userArticles) {
            listId.add(entity.getResourceId());
        }
        if (listId.isEmpty()) {
            return new PageInfo(userArticles);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> articles = articleDao.listArticlesByIds(listId);
        List<UserCollectVO> resultList = new ArrayList<>();
        UserCollectVO articleVO;
        for (ArticleEntity entity : articles) {
            articleVO = new UserCollectVO();
            articleVO.setId(entity.getId());
            articleVO.setTitle(entity.getTitle());
            articleVO.setType("文章");
            resultList.add(articleVO);
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
