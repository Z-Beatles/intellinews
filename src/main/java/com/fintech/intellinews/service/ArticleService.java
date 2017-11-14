package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.*;
import com.fintech.intellinews.entity.*;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.vo.ArticleVO;
import com.fintech.intellinews.vo.CommentVO;
import com.fintech.intellinews.vo.DetailsArticleVO;
import com.fintech.intellinews.vo.SearchArticleVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    private UserLoginDao userLoginDao;


    @SuppressWarnings("unchecked")
    public PageInfo<ArticleVO> listArticlesByChannelId(Long channelId, int pageNum, int pageSize) {
        if (channelId == 1) {
            return listLatestArticles(pageNum, pageSize);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleChannelEntity> articleChannelEntities = articleChannelDao.listByChannelId(channelId);

        List<ArticleVO> articleDTOS = new ArrayList<>();
        for (ArticleChannelEntity articleChannelEntity : articleChannelEntities) {
            Long articleId = articleChannelEntity.getArticleId();
            ArticleEntity articleEntity = articleDao.getById(articleId);
            ArticleCountEntity articleCountEntity = articleCountDao.getByArticleId(articleId);
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
    private PageInfo<ArticleVO> listLatestArticles(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> articleEntities = articleDao.listLatestArticles();
        List<ArticleVO> articleDTOS = new ArrayList<>();
        for (ArticleEntity articleEntity : articleEntities) {
            Long articleId = articleEntity.getId();
            ArticleCountEntity articleCountEntity = articleCountDao.getByArticleId(articleId);
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

    /**
     * 通过关键字获取文章列表
     *
     * @param keyword  关键字
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 分页文章列表
     */
    @SuppressWarnings("unchecked")
    public PageInfo<SearchArticleVO> listArticlesByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<ArticleEntity> searchList = articleDao.listArticleByKeyword(keyword);
        if (searchList == null || searchList.isEmpty()) {
            return new PageInfo<>();
        }
        List<SearchArticleVO> resultList = new ArrayList<>();
        SearchArticleVO articleVO;
        String content;
        Integer showContentSize = 0;
        for (ArticleEntity entity : searchList) {
            articleVO = new SearchArticleVO();
            articleVO.setId(entity.getId());
            articleVO.setTitle(entity.getTitle());
            articleVO.setSource(entity.getSource());
            content = entity.getContent();
            if (content != null) {
                showContentSize = content.length() > 30 ? 30 : content.length();
            }
            articleVO.setContent(entity.getContent().substring(0, showContentSize));
            resultList.add(articleVO);
        }
        PageInfo pageInfo = new PageInfo(searchList);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    /**
     * 根据id获取文章详情
     *
     * @param id 文章id
     * @return 文章详情实体
     */
    public DetailsArticleVO getDetailsArticleById(Long id) {
        ArticleEntity articleEntity = articleDao.getById(id);
        DetailsArticleVO details = new DetailsArticleVO();
        BeanUtils.copyProperties(articleEntity, details);
        String dateStr = DateUtil.toDetailTimeString(articleEntity.getGmtCreate());
        details.setDate(dateStr);
        return details;
    }

    /**
     * 获取指定文章的评论信息
     *
     * @param id       文章id
     * @param pageNum  分页索引
     * @param pageSize 分页椰页容
     * @return 评论的分页信息
     */
    @SuppressWarnings("unchecked")
    public PageInfo<CommentVO> listArticleComments(Long id, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentEntity> comments = commentDao.listArticleComments(id);
        if (comments == null || comments.isEmpty()) {
            return new PageInfo<>();
        }
        List<Long> userIds = new ArrayList<>();
        for (CommentEntity entity : comments) {
            userIds.add(entity.getUserId());
        }
        Map<Long, UserLoginEntity> mapUsers = userLoginDao.mapUserLoginByIds(userIds);
        List<CommentVO> resultComments = new ArrayList<>();
        UserLoginEntity userLoginEntity;
        CommentVO commentVO;
        for (CommentEntity entity : comments) {
            commentVO = new CommentVO();
            BeanUtils.copyProperties(entity, commentVO);
            userLoginEntity = mapUsers.get(entity.getUserId());
            commentVO.setNickName(userLoginEntity.getNickname());
            commentVO.setAvatar(userLoginEntity.getAvatar());
            resultComments.add(commentVO);
        }
        PageInfo pageInfo = new PageInfo(comments);
        pageInfo.setList(resultComments);
        return pageInfo;
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

    @Autowired
    public void setUserLoginDao(UserLoginDao userLoginDao) {
        this.userLoginDao = userLoginDao;
    }

}
