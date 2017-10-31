package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserSetupEntity;

/**
 * @author wanghao
 * create 2017-10-30 17:45
 **/
public interface UserSetupDao extends BaseDao<UserSetupEntity>{

    UserSetupEntity getCurrentUserSetup(Long id);

}
