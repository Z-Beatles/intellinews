package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Result;
import com.fintech.intellinews.dao.UserConfigDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
@Service
public class UserConfigService {
    private UserConfigDao userConfigDao;

    public UserConfigEntity getUserConfig(Long userId){
        if (userId == null||userId < 0){
            throw new AppException(ResultEnum.ACCOUNT_NOTEXIST_ERROR.getCode(),"账户不能再");
        }
        UserConfigEntity userConfigEntity = userConfigDao.getCurrentUserConfig(userId);
        if (userConfigEntity == null){
            throw new AppException(ResultEnum.NULL_OBJECT_ERROR.getCode(),"账户不能再");
        }
        return userConfigEntity;
    }

    @Autowired
    public void setUserConfigDao(UserConfigDao userConfigDao) {
        this.userConfigDao = userConfigDao;
    }
}
