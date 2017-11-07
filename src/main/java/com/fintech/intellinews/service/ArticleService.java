package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    private ArticleChannelDao articleChannelDao;

    private ArticleCountDao articleCountDao;

    @SuppressWarnings("unchecked")
    public PageInfo<ArticleVO> getArticlesByChannelId(Long channelId, int pageNum, int pageSize) {
        ArticleChannelEntity entity = new ArticleChannelEntity();
        entity.setChannelId(channelId);
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleChannelEntity> articleChannelEntities = articleChannelDao.select(entity);

        List<ArticleVO> articleDTOS = new ArrayList<>();
        for (ArticleChannelEntity articleChannelEntity : articleChannelEntities) {
            Long articleId = articleChannelEntity.getArticleId();

            ArticleEntity articleEntity = new ArticleEntity();
            articleEntity.setId(articleId);
            ArticleEntity articleEntity1 = articleDao.select(articleEntity).get(0);

            ArticleCountEntity articleCountEntity = new ArticleCountEntity();
            articleCountEntity.setArticleId(articleId);
            ArticleCountEntity articleCountEntity1 = articleCountDao.select(articleCountEntity).get(0);

            ArticleVO articleDTO = new ArticleVO();
            articleDTO.setId(articleEntity1.getId());
            articleDTO.setTitle(articleEntity1.getTitle());
            articleDTO.setSource(articleEntity1.getSource());
            Date gmtCreate = articleEntity1.getGmtCreate();
            articleDTO.setDate(DateUtil.toCustomStringFromDate(gmtCreate));
            String keywords = articleEntity1.getKeywords();
            articleDTO.setKeywords(JacksonUtil.toArrayNodeFromString(objectMapper, keywords));
            articleDTO.setViewCount(articleCountEntity1.getViewCount());
            articleDTO.setThumbnail(articleEntity1.getThumbnail());
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
