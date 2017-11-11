package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.util.StringUtil;
import com.fintech.intellinews.vo.ListSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-08 15:06
 **/
@Service
public class SectionService {

    private SectionDao sectionDao;

    @SuppressWarnings("unchecked")
    public PageInfo<ListSectionVO> listSections(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SectionEntity> sectionEntities = sectionDao.listAll();
        List<ListSectionVO> resultList = new ArrayList<>();
        ListSectionVO listSectionVO;
        for (SectionEntity sectionEntity : sectionEntities) {
            listSectionVO = new ListSectionVO();
            BeanUtils.copyProperties(sectionEntity, listSectionVO);
            resultList.add(listSectionVO);
        }
        PageInfo pageInfo = new PageInfo(sectionEntities);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    @SuppressWarnings("unchecked")
    public PageInfo<ListSectionVO> listSectionsByKeyword(String keyword, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        // 用 % 分割关键字用于模糊查询
        String signKeyword = StringUtil.spiltString(keyword);
        List<SectionEntity> sectionEntities = sectionDao.listSectionsByKeyword(signKeyword);
        if (sectionEntities == null) {
            return new PageInfo<>();
        }
        List<ListSectionVO> resultList = new ArrayList<>();
        ListSectionVO listSectionVO;
        for (SectionEntity sectionEntity : sectionEntities) {
            listSectionVO = new ListSectionVO();
            BeanUtils.copyProperties(sectionEntity, listSectionVO);
            resultList.add(listSectionVO);
        }
        PageInfo pageInfo = new PageInfo(sectionEntities);
        pageInfo.setList(resultList);
        return pageInfo;
    }

    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }

}
