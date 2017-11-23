package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.UserKeywordDao;
import com.fintech.intellinews.entity.UserKeywordEntity;
import com.fintech.intellinews.vo.UserKeywordVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:21
 **/
@Service
public class UserKeywordService {

    private UserKeywordDao userKeywordDao;

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

    public UserKeywordVO addUserKeyword(){

        return null;
    }

    @Autowired
    public void setUserKeywordDao(UserKeywordDao userKeywordDao) {
        this.userKeywordDao = userKeywordDao;
    }
}
