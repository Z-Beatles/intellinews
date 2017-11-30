package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.dao.UserKeywordDao;
import com.fintech.intellinews.entity.UserKeywordEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.vo.UserKeywordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author wanghao
 * create 2017-11-23 11:21
 **/
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
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
    @Transactional
    public UserKeywordVO addUserKeyword(Long userId,String keyword){
        UserKeywordEntity entity = new UserKeywordEntity();
        entity.setAttention(1);
        entity.setUserId(userId);
        entity.setKeyword(keyword);
        entity.setGmtCreate(Calendar.getInstance().getTime());
        Integer count = userKeywordDao.addUserKeyword(entity);
        if (count == 0){
            throw new AppException(ResultEnum.FAILED.getCode(),"添加用户偏好失败");
        }
        UserKeywordVO userKeywordVO = new UserKeywordVO();
        BeanUtils.copyProperties(entity,userKeywordVO);
        return userKeywordVO;
    }

    /**
     * 更新用户偏好关注度
     * @param userId 用户id
     * @param keyword 偏好关键字
     * @return 成功返回用户添加对象，失败抛出异常
     */
    @Transactional
    public UserKeywordVO updateHobbyAttention(Long userId,String keyword){
        UserKeywordEntity entity = new UserKeywordEntity();
        entity.setUserId(userId);
        entity.setKeyword(keyword);
        Integer updateCount = userKeywordDao.updateHobbyAttention(entity);
        if (updateCount == 0){
            return addUserKeyword(userId,keyword);
        }
        UserKeywordVO userKeywordVO = new UserKeywordVO();
        BeanUtils.copyProperties(entity,userKeywordVO);
        if (true){
            throw new AppException(ResultEnum.FAILED.getCode(),"测试事物");
        }
        return userKeywordVO;
    }

    @Autowired
    public void setUserKeywordDao(UserKeywordDao userKeywordDao) {
        this.userKeywordDao = userKeywordDao;
    }
}
