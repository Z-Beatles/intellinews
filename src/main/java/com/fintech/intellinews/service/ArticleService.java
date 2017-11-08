package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ArticleChannelDao;
import com.fintech.intellinews.dao.ArticleCountDao;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.vo.ArticleVO;
import com.fintech.intellinews.entity.ArticleChannelEntity;
import com.fintech.intellinews.entity.ArticleCountEntity;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
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
    private ObjectMapper objectMapper;

    private ArticleDao articleDao;

    private ArticleChannelDao articleChannelDao;

    private ArticleCountDao articleCountDao;

    @SuppressWarnings("unchecked")
    public PageInfo<ArticleVO> getArticlesByChannelId(Long channelId, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleChannelEntity> articleChannelEntities = articleChannelDao.listByChannelId(channelId);

        List<ArticleVO> articleDTOS = new ArrayList<>();
        for (ArticleChannelEntity articleChannelEntity : articleChannelEntities) {
            Long articleId = articleChannelEntity.getArticleId();
            ArticleEntity articleEntity = articleDao.selectById(articleId);
            ArticleCountEntity articleCountEntity = articleCountDao.selectByArticleId(articleId);
            String date = DateUtil.toCustomStringFromDate(articleEntity.getGmtCreate());
            ArrayNode keywords = JacksonUtil.toArrayNodeFromString(objectMapper, articleEntity.getKeywords());

            ArticleVO articleDTO = new ArticleVO();
            articleDTO.setId(articleEntity.getId());
            articleDTO.setTitle(articleEntity.getTitle());
            articleDTO.setSource(articleEntity.getSource());
            articleDTO.setDate(date);
            articleDTO.setKeywords(keywords);
            articleDTO.setViewCount(articleCountEntity.getViewCount());
            articleDTO.setThumbnail(articleEntity.getThumbnail());
            articleDTOS.add(articleDTO);
        }
        PageInfo page = new PageInfo(articleChannelEntities);
        page.setList(articleDTOS);
        return page;
    }

    public PageInfo<ArticleVO> getArticlesByKeyword(String keyword) {
        return null;
    }


    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Autowired
    public void setArticleChannelDao(ArticleChannelDao articleChannelDao) {
        this.articleChannelDao = articleChannelDao;
    }

    @Autowired
    public void setArticleCountDao(ArticleCountDao articleCountDao) {
        this.articleCountDao = articleCountDao;
    }
}
