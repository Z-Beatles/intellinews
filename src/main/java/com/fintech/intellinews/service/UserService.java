package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.config.AppProperties;
import com.fintech.intellinews.dao.UserInfoDao;
import com.fintech.intellinews.dao.UserLoginDao;
import com.fintech.intellinews.entity.UserInfoEntity;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.RegexUtil;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService extends BaseService {

    private AppProperties appProperties;

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

    @Transactional
    public String register(String username, String password) {
        UserLoginEntity entity = new UserLoginEntity();
        entity.setUsername(username);
        List<UserLoginEntity> entities = userLoginDao.select(entity);
        if (!entities.isEmpty()) {
            throw new AppException(ResultEnum.ACCOUNT_EXIST_ERROR);
        }
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        String algorithmName = appProperties.getAlgorithmName();
        int hashIterations = appProperties.getHashIterations();
        String hexPassword = new SimpleHash(algorithmName, password, salt, hashIterations).toHex();

        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(username);
        userLoginEntity.setPasswordHash(hexPassword);
        userLoginEntity.setPasswordSalt(salt);
        userLoginEntity.setPasswordAlgo(algorithmName);
        userLoginEntity.setPasswordIteration(hashIterations);
        userLoginEntity.setGmtCreate(new Date());
        userLoginDao.insert(userLoginEntity);

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(userLoginEntity.getId());
        userInfoEntity.setUsername(username);
        userInfoEntity.setGmtCreate(new Date());
        userInfoDao.insert(userInfoEntity);
        return username;
    }


    @Autowired
    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
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
