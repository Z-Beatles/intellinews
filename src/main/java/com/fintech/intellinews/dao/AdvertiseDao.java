package com.fintech.intellinews.dao;

import com.fintech.intellinews.base.BaseDao;
import com.fintech.intellinews.entity.AdvertiseEntity;

import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 10:53
 **/
public interface AdvertiseDao extends BaseDao<AdvertiseEntity> {

    /**
     * 查询所有上架的广告
     * @param entity 实体对象
     * @return
     */
    List<AdvertiseEntity> selectAdvertisements(AdvertiseEntity entity);

}
