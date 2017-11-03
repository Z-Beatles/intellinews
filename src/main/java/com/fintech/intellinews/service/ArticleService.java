package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dto.ArticleDTO;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-30 17:29
 */
@Service
public class ArticleService extends BaseService {
    private ObjectMapper objectMapper;

    private ArticleDao articleDao;

    @SuppressWarnings("unchecked")
    public PageInfo<ArticleDTO> getArticlesByCategoryId(Long categoryId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> articles = articleDao.getArticlesByCategoryId(categoryId);
        List<ArticleDTO> articleDTOS = new ArrayList<>();
        for (ArticleEntity article : articles) {
            ArticleDTO articleDTO = new ArticleDTO();
            articleDTO.setId(article.getId());
            articleDTO.setTitle(article.getTitle());
            articleDTO.setSource(article.getSource());
            Date gmtCreate = article.getGmtCreate();
            articleDTO.setDate(DateUtil.toCustomStringFromDate(gmtCreate));
            String keywords = article.getKeywords();
            articleDTO.setKeywords(JacksonUtil.toArrayNodeFromString(objectMapper, keywords));
            articleDTO.setViewCount(article.getViewCount());
            articleDTO.setThumbnail(article.getThumbnail());
            articleDTOS.add(articleDTO);
        }
        PageInfo page = new PageInfo(articles);
        page.setList(articleDTOS);
        return page;
    }

    public List<ArticleEntity> getArticlesByKeyword(String keyword) {
        return null;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }
}
