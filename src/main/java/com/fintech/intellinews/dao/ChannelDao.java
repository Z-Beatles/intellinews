package com.fintech.intellinews.dao;

import com.fintech.intellinews.entity.ChannelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelDao {

    /**
     * 获取所有频道列表
     *
     * @return 频道列表
     */
    List<ChannelEntity> listAll();
}
