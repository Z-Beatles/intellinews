package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.entity.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-30 17:29
 */
@Service
public class ArticleService extends BaseService{

    private ArticleDao articleDao;

    public List<ArticleEntity> getArticlesByCategoryId(Long categoryId) {
        return articleDao.getArticlesByCategoryId(categoryId);
    }


    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }
}
