package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.dao.UserSectionDao;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.entity.UserSectionEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.vo.UserSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author wanghao
 * create 2017-11-13 16:10
 **/
@Service
public class UserSectionService {

    private UserSectionDao userSectionDao;

    private SectionDao sectionDao;

    /**
     * 获取用户收藏条目
     * @param userId 用户id
     * @param pageNum 搜索页数
     * @param pageSize 搜索条数
     * @return 收藏列表
     */
    @SuppressWarnings("unchecked")
    public PageInfo<UserSectionVO> getUserSections(Long userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<UserSectionEntity> userSections = userSectionDao.getUserSection(userId);
        if (userSections==null||userSections.size()==0){
            return new PageInfo(userSections);
        }
        List<Long> idList = new ArrayList<>();
        for (UserSectionEntity entity : userSections){
            idList.add(entity.getSectionId());
        }
        Map<Long,SectionEntity> mapList = sectionDao.listSectionByIds(idList);
        List<UserSectionVO> list = new ArrayList<>();
        UserSectionVO userSectionVO;
        SectionEntity sectionEntity;
        for (UserSectionEntity entity : userSections){
            userSectionVO = new UserSectionVO();
            userSectionVO.setId(entity.getSectionId());
            sectionEntity = mapList.get(entity.getSectionId());
            userSectionVO.setLogo(sectionEntity.getLogo());
            userSectionVO.setName(sectionEntity.getName());
            list.add(userSectionVO);
        }
        PageInfo pageInfo = new PageInfo(userSections);
        pageInfo.setList(list);
        return pageInfo;
    }

    /**
     * 添加用户收藏条目
     * @param userId 用户id
     * @param sectionId 条目id
     * @return 影响行数
     */
    public Integer insertUserSection(Long userId,Long sectionId){
        Map<String,Long> param = new HashMap<>();
        param.put("userId",userId);
        param.put("sectionId",sectionId);
        Integer count = userSectionDao.checkUserSection(param);
        if (count>0){
            throw new AppException(ResultEnum.FAILED.getCode(),"收藏失败");
        }
        UserSectionEntity insertSection = new UserSectionEntity();
        insertSection.setUserId(userId);
        insertSection.setSectionId(sectionId);
        insertSection.setGmtCreate(Calendar.getInstance().getTime());
        return userSectionDao.insert(insertSection);
    }

    /**
     * 取消收藏条目
     * @return 影响行数
     */
    public Integer deleteUserSection(Long userId,Long sectionId){
        UserSectionEntity section = new UserSectionEntity();
        section.setUserId(userId);
        section.setSectionId(sectionId);
        return userSectionDao.deleteCollectSection(section);
    }


    @Autowired
    public void setUserSectionDao(UserSectionDao userSectionDao) {
        this.userSectionDao = userSectionDao;
    }

    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }
}
