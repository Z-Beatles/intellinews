package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.UserLoginEntity;
import org.apache.ibatis.annotations.MapKey;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface UserLoginDao extends BaseDao<UserLoginEntity> {

    @MapKey("id")
    Map<Long,UserLoginEntity> mapUserLoginByIds(List<Long> list);

}