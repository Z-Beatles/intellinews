package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.*;
import com.fintech.intellinews.entity.*;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.util.StringUtil;
import com.fintech.intellinews.vo.AtlasVO;
import com.fintech.intellinews.vo.DetailsSectionVO;
import com.fintech.intellinews.vo.ListSectionVO;
import com.fintech.intellinews.vo.SearchSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author wanghao
 * create 2017-11-08 15:06
 **/
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
@SuppressWarnings("unchecked")
public class SectionService extends BaseService {
    private ObjectMapper objectMapper;

    private SectionDao sectionDao;

    private SectionCountDao sectionCountDao;

    private SectionItemDao sectionItemDao;

    private SectionAliasDao sectionAliasDao;

    private AtlasDao atlasDao;

    private ArticleDao articleDao;

    private ArticleCountDao articleCountDao;

    /**
     * 获取所有条目列表
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 条目列表
     */
    public PageInfo<ListSectionVO> listSections(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SectionEntity> sectionEntities = sectionDao.listAll();
        List<ListSectionVO> resultList = new ArrayList<>();
        ListSectionVO listSectionVO;
        SectionCountEntity sectionCountEntity;
        for (SectionEntity sectionEntity : sectionEntities) {
            listSectionVO = new ListSectionVO();
            sectionCountEntity = sectionCountDao.getBySectionId(sectionEntity.getId());
            BeanUtils.copyProperties(sectionEntity, listSectionVO);
            listSectionVO.setViewCount(sectionCountEntity.getViewCount());
            resultList.add(listSectionVO);
        }
        PageInfo pageInfo = new PageInfo(sectionEntities);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    /**
     * 根据关键字查询搜索条目列表
     *
     * @param keyword  关键字
     * @param pageNum  页数
     * @param pageSize 页大小
     * @return 搜索条目列表
     */
    public PageInfo<SearchSectionVO> listByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 用 % 分割关键字用于模糊查询
        String signKeyword = StringUtil.spiltString(keyword);
        List<SectionEntity> sectionEntities = sectionDao.listSectionsByKeyword(signKeyword);
        if (sectionEntities.isEmpty()) {
            return new PageInfo(sectionEntities);
        }
        List<SearchSectionVO> resultList = new ArrayList<>();
        SearchSectionVO searchSectionVO;
        for (SectionEntity sectionEntity : sectionEntities) {
            searchSectionVO = new SearchSectionVO();
            BeanUtils.copyProperties(sectionEntity, searchSectionVO);
            resultList.add(searchSectionVO);
        }
        PageInfo pageInfo = new PageInfo(sectionEntities);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    /**
     * 根据拼音首字母查询导航条目列表（导航查询）
     *
     * @param startWith 拼音首字母
     * @param pageNum   页数
     * @param pageSize  页大小
     * @return 导航条目列表
     */
    public PageInfo<ListSectionVO> listByStartWith(String startWith, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Long> sectionIds = sectionAliasDao.listByStartWith(startWith);
        if (sectionIds.isEmpty()) {
            return new PageInfo(sectionIds);
        }
        Map<Long, SectionEntity> sectionMap = sectionDao.mapSectionByIds(sectionIds);
        Map<Long, SectionCountEntity> sectionCountMap = sectionCountDao.mapSectionCountByIds(sectionIds);
        List<ListSectionVO> listSectionVOS = new ArrayList<>();
        ListSectionVO listSectionVO;
        SectionCountEntity sectionCountEntity;
        for (SectionEntity sectionEntity : sectionMap.values()) {
            listSectionVO = new ListSectionVO();
            sectionCountEntity = sectionCountMap.get(sectionEntity.getId());
            BeanUtils.copyProperties(sectionEntity, listSectionVO);
            BeanUtils.copyProperties(sectionCountEntity, listSectionVO);
            listSectionVOS.add(listSectionVO);
        }
        PageInfo pageInfo = new PageInfo(sectionIds);
        pageInfo.setList(listSectionVOS);
        return pageInfo;
    }

    /**
     * 根据id查询条目详情
     *
     * @param sectionId 条目id
     * @return 条目详情
     */
    @Transactional
    public DetailsSectionVO getSectionById(Long sectionId) {
        SectionEntity sectionEntity = sectionDao.getById(sectionId);
        if (sectionEntity == null) {
            throw new AppException(ResultEnum.SECTION_NOT_EXIST_ERROR);
        }
        SectionCountEntity sectionCountEntity = sectionCountDao.getBySectionId(sectionId);
        SectionItemEntity sectionItemEntity = sectionItemDao.getBySectionId(sectionId);
        ObjectNode itemInfo = JacksonUtil.toObjectNodeFromString(objectMapper, sectionItemEntity.getItemInfo());
        // 创建时间为section主表的创建时间
        String createTime = DateUtil.toCustomStringFromDate(sectionEntity.getGmtCreate());
        // 修改时间为section主表和item扩展信息表中选择最新修改的为修改时间
        Date date = sectionEntity.getGmtModified().after(sectionItemEntity.getGmtModified()) ? sectionEntity
                .getGmtModified() : sectionItemEntity.getGmtModified();
        String updateTime = DateUtil.toCustomStringFromDate(date);

        DetailsSectionVO detailsSectionVO = new DetailsSectionVO();
        BeanUtils.copyProperties(sectionEntity, detailsSectionVO);
        detailsSectionVO.setViewCount(sectionCountEntity.getViewCount());
        detailsSectionVO.setShareCount(sectionCountEntity.getShareCount());
        detailsSectionVO.setCollectCount(sectionCountEntity.getCollectCount());
        detailsSectionVO.setItemInfo(itemInfo);
        detailsSectionVO.setCreateTime(createTime);
        detailsSectionVO.setUpdateTime(updateTime);
        // 更新条目浏览量
        sectionCountDao.updateViewCountBySectionId(sectionId);
        return detailsSectionVO;
    }

    /**
     * 根据条目id查询图谱信息
     *
     * @param sectionId 条目id
     * @return 图谱信息
     */
    public Map<String, Object> listAtlasBySectionId(Long sectionId) {
        Map<String, Object> result = new HashMap();
        List<AtlasEntity> atlasSectionEntities = atlasDao.listBySectionIdAndType(sectionId, "section", 5);
        List<AtlasEntity> atlasArticleEntities = atlasDao.listBySectionIdAndType(sectionId, "article", 3);

        List<Long> sectionIds = new ArrayList<>();
        List<Long> articleIds = new ArrayList<>();
        for (AtlasEntity entity : atlasSectionEntities) {
            sectionIds.add(entity.getRelationId());
        }
        for (AtlasEntity entity : atlasArticleEntities) {
            articleIds.add(entity.getRelationId());
        }
        Map<Long, SectionEntity> sectionMap = new HashMap<>();
        Map<Long, SectionCountEntity> sectionCountMap = new HashMap<>();
        Map<Long, ArticleEntity> articleMap = new HashMap<>();
        Map<Long, ArticleCountEntity> articleCountMap = new HashMap<>();
        if (!sectionIds.isEmpty()) {
            sectionMap = sectionDao.mapSectionByIds(sectionIds);
            sectionCountMap = sectionCountDao.mapSectionCountByIds(sectionIds);
        }
        if (!articleIds.isEmpty()) {
            articleMap = articleDao.mapArticlesByIds(articleIds);
            articleCountMap = articleCountDao.mapArticleCountByIds(articleIds);
        }
        List<AtlasVO> atlasSectionVOS = new ArrayList<>();
        List<AtlasVO> atlasArticleVOS = new ArrayList<>();

        Integer maxSectionViewCount = sectionCountDao.getMaxViewCount();
        Integer maxArticleViewCount = articleCountDao.getMaxViewCount();
        for (AtlasEntity atlasSectionEntity : atlasSectionEntities) {
            AtlasVO atlasSectionVO = new AtlasVO();
            atlasSectionVO.setId(atlasSectionEntity.getRelationId());
            atlasSectionVO.setTitle(sectionMap.get(atlasSectionEntity.getRelationId()).getName());
            atlasSectionVO.setDistance(atlasSectionEntity.getRelationDegree());
            Integer viewCount = sectionCountMap.get(atlasSectionEntity.getRelationId()).getViewCount();
            Integer weight = convertToWeight(viewCount, maxSectionViewCount);
            atlasSectionVO.setWeight(weight);
            atlasSectionVOS.add(atlasSectionVO);
        }
        for (AtlasEntity atlasArticleEntity : atlasArticleEntities) {
            AtlasVO atlasArticleVO = new AtlasVO();
            atlasArticleVO.setId(atlasArticleEntity.getRelationId());
            atlasArticleVO.setTitle(articleMap.get(atlasArticleEntity.getRelationId()).getTitle());
            atlasArticleVO.setDistance(atlasArticleEntity.getRelationDegree());
            Integer viewCount = articleCountMap.get(atlasArticleEntity.getRelationId()).getViewCount();
            Integer weight = convertToWeight(viewCount, maxArticleViewCount);
            atlasArticleVO.setWeight(weight);
            atlasArticleVOS.add(atlasArticleVO);
        }
        SectionEntity sectionEntity = sectionDao.getById(sectionId);
        Map<String, Object> centerInfo = new HashMap<>(2);
        centerInfo.put("id", sectionEntity.getId());
        centerInfo.put("title", sectionEntity.getName());

        result.put("center", centerInfo);
        result.put("section", atlasSectionVOS);
        result.put("articles", atlasArticleVOS);
        return result;
    }

    /**
     * 浏览量转化为权重
     *
     * @param viewCount 浏览量
     * @return 权重
     */
    private Integer convertToWeight(Integer viewCount, Integer maxViewCount) {
        if (viewCount == 0) {
            return 1;
        }
        return viewCount * 50 / maxViewCount;
    }

    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

    @Autowired
    public void setSectionCountDao(SectionCountDao sectionCountDao) {
        this.sectionCountDao = sectionCountDao;
    }

    @Autowired
    public void setSectionItemDao(SectionItemDao sectionItemDao) {
        this.sectionItemDao = sectionItemDao;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setSectionAliasDao(SectionAliasDao sectionAliasDao) {
        this.sectionAliasDao = sectionAliasDao;
    }

    @Autowired
    public void setAtlasDao(AtlasDao atlasDao) {
        this.atlasDao = atlasDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

    @Autowired
    public void setArticleCountDao(ArticleCountDao articleCountDao) {
        this.articleCountDao = articleCountDao;
    }
}
