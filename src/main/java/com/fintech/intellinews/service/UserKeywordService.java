package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.UserKeywordDao;
import com.fintech.intellinews.entity.UserKeywordEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.vo.UserKeywordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:21
 **/
@Service
public class UserKeywordService {

    private UserKeywordDao userKeywordDao;

    /**
     * 获取用户前五十偏好关键字
     * @param userId 用户id
     * @return 偏好关键字列表
     */
    public List<UserKeywordVO> getUserKeyWords(Long userId){
        List<UserKeywordEntity> result = userKeywordDao.getUserKeywords(userId);
        List<UserKeywordVO> returnList = new ArrayList<>();
        UserKeywordVO userKeywordVO ;
        for (UserKeywordEntity entity : result){
            userKeywordVO = new UserKeywordVO();
            BeanUtils.copyProperties(entity,userKeywordVO);
            returnList.add(userKeywordVO);
        }
        return returnList;
    }

    /**
     * 添加用户偏好
     * @param userId 用户id
     * @param keyword 偏好关键字
     * @return 成功返回用户添加对象，失败抛出异常
     */
    public UserKeywordVO addUserKeyword(Long userId,String keyword){
        UserKeywordEntity entity = new UserKeywordEntity();
        entity.setUserId(userId);
        entity.setKeyword(keyword);
        entity.setAttention(1);
        entity.setGmtCreate(Calendar.getInstance().getTime());
        Integer count = userKeywordDao.addUserKeyword(entity);
        if (count == 0){
            throw new AppException(ResultEnum.FAILED.getCode(),"添加用户偏好失败");
        }
        UserKeywordVO userKeywordVO = new UserKeywordVO();
        BeanUtils.copyProperties(entity,userKeywordVO);
        return userKeywordVO;
    }

    @Autowired
    public void setUserKeywordDao(UserKeywordDao userKeywordDao) {
        this.userKeywordDao = userKeywordDao;
    }
}
