package com.fintech.intellinews.service;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.UserSetupDao;
import com.fintech.intellinews.entity.UserSetupEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2017-10-30 17:52
 **/
@Service
public class UserSetupService extends BaseService {

    @Autowired
    private UserSetupDao userSetupDao;

    public Result<UserSetupEntity> getCurrentUserSetup(Long id){
        UserSetupEntity entity = userSetupDao.getCurrentUserSetup(id);
        if (entity == null){
            return ResultUtil.error(ResultEnum.NULL_OBJECT_ERROR);
        }
        return ResultUtil.success(ResultEnum.SUCCESS,entity);
    }

}
