package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.dao.UserFootmarkDao;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.FootmarkEntity;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserFootmarkService;
import com.fintech.intellinews.vo.FootmarkVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanghao
 * create 2017-11-13 13:14
 **/
@Service
@SuppressWarnings("unchecked")
public class UserFootmarkServiceImpl implements UserFootmarkService {

    @Autowired
    private UserFootmarkDao userFootmarkDao;

    @Autowired
    private ArticleDao articleDao;

    @Autowired
    private SectionDao sectionDao;

    /**
     * 获取用户足迹
     *
     * @param userId   用户id
     * @param pageNum  搜索页数
     * @param pageSize 搜索条数
     * @return 足迹列表
     */
    @Override
    public PageInfo<FootmarkVO> getUserFootmarks(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<FootmarkEntity> footmarks = userFootmarkDao.getUserFootmarks(userId);
        if (footmarks.isEmpty()) {
            return new PageInfo(footmarks);
        }
        List<Long> articleList = new ArrayList<>();
        List<Long> sectionList = new ArrayList<>();
        for (FootmarkEntity entity : footmarks) {
            if ("article".equals(entity.getContentType())) {
                articleList.add(entity.getContentId());
            } else if ("section".equals(entity.getContentType())) {
                sectionList.add(entity.getContentId());
            }
        }
        Map<Long, ArticleEntity> articleMap = new HashMap<>();
        Map<Long, SectionEntity> sectionMap = new HashMap<>();
        if (!articleList.isEmpty()) {
            articleMap = articleDao.mapArticlesByIds(articleList);
        }
        if (!sectionList.isEmpty()) {
            sectionMap = sectionDao.mapSectionByIds(sectionList);
        }
        List<FootmarkVO> footmarkVOS = new ArrayList<>();
        FootmarkVO footmarkVO;
        String content;
        ArticleEntity articleEntity;
        SectionEntity sectionEntity;
        for (FootmarkEntity entity : footmarks) {
            footmarkVO = new FootmarkVO();
            BeanUtils.copyProperties(entity, footmarkVO);
            exchangeDate(footmarkVO, entity);
            if ("article".equals(entity.getContentType()) && !articleMap.isEmpty()) {
                articleEntity = articleMap.get(entity.getContentId());
                content = articleEntity.getTitle();
            } else if ("section".equals(entity.getContentType()) && !sectionMap.isEmpty()) {
                sectionEntity = sectionMap.get(entity.getContentId());
                content = sectionEntity.getName();
            } else {
                continue;
            }
            footmarkVO.setContent(content);
            footmarkVOS.add(footmarkVO);
        }
        PageInfo pageInfo = new PageInfo(footmarks);
        pageInfo.setList(footmarkVOS);
        return pageInfo;
    }

    /**
     * 添加用户足迹
     *
     * @param userId      用户id
     * @param contentId   足迹内容id
     * @param source      足迹来源
     * @param contentType 足迹内容类型
     * @return 足迹id
     */
    @Override
    public Long addFootmark(Long userId, Long contentId, String source, String contentType) {
        FootmarkEntity entity = new FootmarkEntity();
        entity.setUserId(userId);
        entity.setContentType(contentType);
        entity.setContentId(contentId);
        entity.setGmtCreate(Calendar.getInstance().getTime());
        entity.setSource(source);
        Integer count = userFootmarkDao.insertUserFootmark(entity);
        if (count == 0) {
            throw new AppException(ResultEnum.INSERT_USER_FOOTMARK_FAILED_ERROR);
        }
        return entity.getId();
    }

    /**
     * 删除用户足迹
     *
     * @param footmarkId 足迹id
     * @return 足迹id
     */
    @Override
    public Long deleteUserFootmark(Long footmarkId) {
        Integer count = userFootmarkDao.deleteUserFootmarkById(footmarkId);
        if (count == 0) {
            throw new AppException(ResultEnum.DELETE_USER_FOOTMARK_FAILED_ERROR);
        }
        return footmarkId;
    }

    /**
     * 转换时间格式
     *
     * @param footmarkVO 足迹VO
     * @param entity     足迹对象
     */
    private void exchangeDate(FootmarkVO footmarkVO, FootmarkEntity entity) {
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        date.setTime(entity.getGmtCreate());
        footmarkVO.setYear(String.valueOf(date.get(Calendar.YEAR)));
        if (year == date.get(Calendar.YEAR) && month == date.get(Calendar.MONTH)) {
            switch (day - date.get(Calendar.DATE)) {
                case 0:
                    footmarkVO.setDate("今天");
                    break;
                case 1:
                    footmarkVO.setDate("昨天");
                    break;
                case 2:
                    footmarkVO.setDate("前天");
                    break;
                default:
                    footmarkVO.setDate(month + ":" + date.get(Calendar.DATE));
                    break;
            }
        } else {
            footmarkVO.setDate(date.get(Calendar.MONTH) + "-" + date.get(Calendar.DATE));
        }
        footmarkVO.setTime(date.get(Calendar.HOUR) + ":" + date.get(Calendar.MINUTE));
    }
}
