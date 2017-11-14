package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.SectionAliasDao;
import com.fintech.intellinews.dao.SectionCountDao;
import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.dao.SectionItemDao;
import com.fintech.intellinews.entity.SectionCountEntity;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.entity.SectionItemEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.util.RegexUtil;
import com.fintech.intellinews.util.StringUtil;
import com.fintech.intellinews.vo.DetailsSectionVO;
import com.fintech.intellinews.vo.ListSectionVO;
import com.fintech.intellinews.vo.SearchSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-08 15:06
 **/
@Service
public class SectionService extends BaseService {
    private ObjectMapper objectMapper;

    private SectionDao sectionDao;

    private SectionCountDao sectionCountDao;

    private SectionItemDao sectionItemDao;

    private SectionAliasDao sectionAliasDao;

    @SuppressWarnings("unchecked")
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

    @SuppressWarnings("unchecked")
    public PageInfo<ListSectionVO> listSectionsByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        if (RegexUtil.matchStartWith(keyword)) {
            return listSectionsByStartWith(keyword, pageNum, pageSize);
        }
        PageHelper.startPage(pageNum, pageSize);
        // 用 % 分割关键字用于模糊查询
        String signKeyword = StringUtil.spiltString(keyword);
        List<SectionEntity> sectionEntities = sectionDao.listSectionsByKeyword(signKeyword);
        if (sectionEntities == null) {
            return new PageInfo<>();
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

    private PageInfo<ListSectionVO> listSectionsByStartWith(String startWith, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // List<SectionEntity> sectionEntities = sectionItemDao.listSectionsB`yStartWith(startWith);
        return null;
    }

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
        return detailsSectionVO;
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

    public void setSectionAliasDao(SectionAliasDao sectionAliasDao) {
        this.sectionAliasDao = sectionAliasDao;
    }
}
