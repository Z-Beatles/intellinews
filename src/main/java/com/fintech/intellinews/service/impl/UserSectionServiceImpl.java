package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.dao.UserSectionDao;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.entity.UserSectionEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserSectionService;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.vo.UserSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

/**
 * @author wanghao
 * create 2017-11-13 16:10
 **/
@Service
public class UserSectionServiceImpl implements UserSectionService {

    @Autowired
    private UserSectionDao userSectionDao;

    @Autowired
    private SectionDao sectionDao;

    /**
     * 获取条目指定用户的收藏
     *
     * @param userId    用户id
     * @param sectionId 条目id
     * @return 收藏资源
     */
    @Override
    public UserSectionEntity getUserSectionCollect(Long userId, Long sectionId) {
        UserSectionEntity userSectionEntity = new UserSectionEntity();
        userSectionEntity.setSectionId(sectionId);
        userSectionEntity.setUserId(userId);
        UserSectionEntity userSection = userSectionDao.getUserSectionCollect(userSectionEntity);
        if (userSection == null) {
            throw new AppException(ResultEnum.SECTION_NOT_COLLECT_ERROR);
        }
        return userSection;
    }

    /**
     * 获取用户收藏条目
     *
     * @param userId   用户id
     * @param pageNum  搜索页数
     * @param pageSize 搜索条数
     * @return 收藏列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageInfo<UserSectionVO> getUserSections(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserSectionEntity> userSections = userSectionDao.getUserSection(userId);
        if (userSections == null || userSections.isEmpty()) {
            return new PageInfo(userSections);
        }
        List<Long> idList = new ArrayList<>();
        for (UserSectionEntity entity : userSections) {
            idList.add(entity.getSectionId());
        }
        Map<Long, SectionEntity> mapList = sectionDao.mapSectionByIds(idList);
        List<UserSectionVO> list = new ArrayList<>();
        UserSectionVO userSectionVO;
        SectionEntity sectionEntity;
        for (UserSectionEntity entity : userSections) {
            userSectionVO = new UserSectionVO();
            userSectionVO.setId(entity.getSectionId());
            sectionEntity = mapList.get(entity.getSectionId());
            userSectionVO.setLogo(sectionEntity.getLogo());
            userSectionVO.setName(sectionEntity.getName());
            userSectionVO.setDate(DateUtil.toCustomStringFromDate(sectionEntity.getGmtCreate()));
            list.add(userSectionVO);
        }
        PageInfo pageInfo = new PageInfo(userSections);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 用户收藏条目
     *
     * @param userId    用户id
     * @param sectionId 条目id
     * @return 影响行数
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer insertUserSection(Long userId, Long sectionId) {
        Integer count = userSectionDao.checkUserSection(userId, sectionId);
        if (count > 0) {
            throw new AppException(ResultEnum.COLLECT_SECTION_REPEAT_ERROR);
        }
        UserSectionEntity insertSection = new UserSectionEntity();
        insertSection.setUserId(userId);
        insertSection.setSectionId(sectionId);
        insertSection.setGmtCreate(Calendar.getInstance().getTime());
        return userSectionDao.insertUserSection(insertSection);
    }

    /**
     * 取消收藏条目
     *
     * @return 影响行数
     */
    @Override
    public Integer deleteUserSection(Long userId, Long sectionId) {
        UserSectionEntity section = new UserSectionEntity();
        section.setUserId(userId);
        section.setSectionId(sectionId);
        return userSectionDao.deleteCollectSection(section);
    }
}
