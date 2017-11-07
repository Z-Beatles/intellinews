package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.UserInfoDao;
import com.fintech.intellinews.dao.UserLoginDao;
import com.fintech.intellinews.entity.UserInfoEntity;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.util.RegexUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Service
public class UserService extends BaseService {

    private UserInfoDao userInfoDao;

    private UserLoginDao userLoginDao;

    public UserLoginEntity getByAccount(String account) {
        UserInfoEntity userInfo = new UserInfoEntity();
        if (RegexUtil.matchMobile(account)) {
            userInfo.setPhone(account);
        } else if (RegexUtil.matchEmail(account)) {
            userInfo.setEmail(account);
        } else {
            userInfo.setUsername(account);
        }
        List<UserInfoEntity> userInfoEntities = userInfoDao.select(userInfo);
        if (userInfoEntities == null) {
            return null;
        }
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(userInfoEntities.get(0).getUsername());
        return userLoginDao.select(userLoginEntity).get(0);
    }

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Autowired
    public void setUserLoginDao(UserLoginDao userLoginDao) {
        this.userLoginDao = userLoginDao;
    }
}
