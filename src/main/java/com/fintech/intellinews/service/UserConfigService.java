package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Result;
import com.fintech.intellinews.dao.UserConfigDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
@Service
public class UserConfigService {
    private UserConfigDao userConfigDao;

    public PageInfo<List<UserConfigEntity>> listConfig(Integer pageNum,Integer pageSize){
        if (pageNum == null || pageNum < 1){
            pageNum = 1;
        }
        PageHelper.startPage(pageNum,pageSize);
        List<UserConfigEntity> list = userConfigDao.getSortEntity();
        PageInfo<List<UserConfigEntity>> pageInfo = new PageInfo<>();

        return pageInfo;
    }

    public UserConfigEntity getUserConfig(Long userId){
        if (userId == null||userId < 0){
            throw new AppException(ResultEnum.ACCOUNT_NOTEXIST_ERROR.getCode(),"账户不存在");
        }
        UserConfigEntity userConfigEntity = userConfigDao.getCurrentUserConfig(userId);
        if (userConfigEntity == null){
            throw new AppException(ResultEnum.NULL_OBJECT_ERROR.getCode(),"未查询到数据");
        }
        return userConfigEntity;
    }

    @Autowired
    public void setUserConfigDao(UserConfigDao userConfigDao) {
        this.userConfigDao = userConfigDao;
    }
}
