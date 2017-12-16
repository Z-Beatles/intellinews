package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
public interface UserConfigService {

    /**
     * 获取指定用户的频道配置
     *
     * @param id 用户id
     * @return 配置对象
     */
    ArrayNode getUserChannelConfig(Long id);

    /**
     * 更新指定用户的频道配置
     *
     * @param id     用户id
     * @param config 用户配置json
     * @return 配置对象
     */
    ArrayNode updateUserChannelConfig(Long id, String config);
}
