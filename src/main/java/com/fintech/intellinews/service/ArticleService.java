package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dto.ArticleDTO;
import com.fintech.intellinews.entity.ArticleEntity;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-30 17:29
 */
@Service
public class ArticleService extends BaseService {

    private ArticleDao articleDao;

    public PageInfo<ArticleDTO> getArticlesByCategoryId(Long categoryId,int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> articles = articleDao.getArticlesByCategoryId(categoryId);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (ArticleEntity article : articles) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setSource(article.getSource());
            articleDTO.setDate(article.getGmtCreate().toString());
            articleDTO.setKeywords(article.getKeywords());
            articleDTO.setViewCount(article.getBrowseCount());
            articleDTO.setThumbnail(article.getThumbnail());
            articleDTOS.add(articleDTO);
        }
        return new PageInfo<>(articleDTOS);
    }

    public List<ArticleEntity> getArticlesByKeyword(String keyword) {
        return null;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }


}
