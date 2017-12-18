package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.AdvertiseEntity;

import java.util.List;

public interface AdvertiseDao {

    /**
     * 获取广告列表
     *
     * @param entity 广告对象
     * @return 广告列表
     */
    List<AdvertiseEntity> listAdvertises(AdvertiseEntity entity);
}
