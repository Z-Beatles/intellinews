package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ArticleChannelDao;
import com.fintech.intellinews.dao.ArticleCountDao;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.vo.*;
import com.fintech.intellinews.entity.ArticleChannelEntity;
import com.fintech.intellinews.entity.ArticleCountEntity;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.vo.ArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

    private CommentDao commentDao;

    /**
     * 计算文章详情显示时间
     */
    private static String[] TIME_UNIT = {"秒","分钟","小时"};

    @SuppressWarnings("unchecked")
    public PageInfo<ArticleVO> getArticlesByChannelId(Long channelId, int pageNum, int pageSize) {
        if (channelId == 1) {
            return getLatestArticles(pageNum, pageSize);
        }
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

    @SuppressWarnings("unchecked")
    private PageInfo<ArticleVO> getLatestArticles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> articleEntities = articleDao.listLatestArticles();
        List<ArticleVO> articleDTOS = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            Long articleId = articleEntity.getId();
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
        PageInfo page = new PageInfo(articleEntities);
        page.setList(articleDTOS);
        return page;
    }

    @SuppressWarnings("unchecked")
    public PageInfo<SearchArticleVO> getArticlesByKeyword(String keyword,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<ArticleEntity> searchList = articleDao.listArticleByKeyword(keyword);
        if (searchList == null){
            return new PageInfo<>();
        }

        List<SearchArticleVO> resultList = new ArrayList<>();
        SearchArticleVO articleVO ;
        String content ;
        Integer showContentSize = 0;
        for(ArticleEntity entity : searchList){
            articleVO = new SearchArticleVO();
            articleVO.setId(entity.getId());
            articleVO.setTitle(entity.getContent());
            articleVO.setSource(entity.getSource());
            content = entity.getContent();
            if (content != null){
                showContentSize = content.length() > 30?30:content.length();
            }
            articleVO.setContent(entity.getContent().substring(0,showContentSize));
            articleVO.setDate(entity.getGmtCreate());
            resultList.add(articleVO);
        }
        PageInfo pageInfo = new PageInfo(searchList);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    /**
     * 根据id获取文章详情
     * @param id 文章id
     * @return 文章详情实体
     */
    public DetailsArticleVO getDetailsArticleById(Long id){
        ArticleEntity articleEntity = articleDao.selectById(id);
        DetailsArticleVO details = new DetailsArticleVO();
        BeanUtils.copyProperties(articleEntity,details);
        Long current = System.currentTimeMillis();
        Long interval = current - articleEntity.getGmtCreate().getTime();
        System.out.println(interval);
        Long time = interval / 1000;
        //如果创建时间大于一天则直接显示日期
        if (time > 60*60*24){
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            details.setDate(dateFormat.format(articleEntity.getGmtCreate()));
            return details;
        }
        //创建时间小于一天
        StringBuilder timeDesc = new StringBuilder();
        Long temp;
        for (int i = 0;i<TIME_UNIT.length;i++){
            temp = time % 60;
            time /= 60;
            if (temp < 60 && time == 0){
                timeDesc.insert(0,temp+TIME_UNIT[i]);
                break;
            }
            timeDesc.insert(0,temp+TIME_UNIT[i]);
        }
        details.setDate(timeDesc.toString());
        return details;
    }

    /**
     * 获取指定文章的评论信息
     * @param articleId 文章id
     * @param pageNum 分页索引
     * @param PageSize 分页椰页容
     * @return 评论的分页信息
     */
    public PageInfo<CommentVO> getArticleComments(Long articleId,Integer pageNum,Integer PageSize){
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

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }
}
