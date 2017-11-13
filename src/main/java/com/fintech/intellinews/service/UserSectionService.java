package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.SectionDao;
import com.fintech.intellinews.dao.UserSectionDao;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.entity.UserSectionEntity;
import com.fintech.intellinews.vo.UserSectionVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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


    @Autowired
    public void setUserSectionDao(UserSectionDao userSectionDao) {
        this.userSectionDao = userSectionDao;
    }

    @Autowired
    public void setSectionDao(SectionDao sectionDao) {
        this.sectionDao = sectionDao;
    }
}
