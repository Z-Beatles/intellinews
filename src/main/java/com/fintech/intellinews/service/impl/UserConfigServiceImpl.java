package com.fintech.intellinews.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.dao.UserConfigDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserConfigService;
import com.fintech.intellinews.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
@Service
public class UserConfigServiceImpl implements UserConfigService {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserConfigDao userConfigDao;

    /**
     * 获取指定用户的频道配置
     *
     * @param id 用户id
     * @return 配置对象
     */
    @Cacheable(cacheNames = "userChannelConfig", key = "#id")
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ArrayNode getUserChannelConfig(Long id) {
        String config = userConfigDao.getUserChannelConfig(id);
        if (config == null) {
            String defaultConfig = Constant.DEFAULT_USER_CHANNEL_CONFIG;
            // 初始化用户频道配置
            userConfigDao.insertUserConfig(id, defaultConfig);
            return JacksonUtil.toArrayNodeFromString(objectMapper, defaultConfig);
        }
        return JacksonUtil.toArrayNodeFromString(objectMapper, config);
    }

    /**
     * 更新指定用户的频道配置
     *
     * @param id     用户id
     * @param config 用户配置json
     * @return 配置对象
     */
    @CacheEvict(cacheNames = "userChannelConfig", key = "#id")
    @Override
    public ArrayNode updateUserChannelConfig(Long id, String config) {
        ArrayNode configNode;
        try {
            configNode = JacksonUtil.toArrayNodeFromString(objectMapper, config);
        } catch (Exception e) {
            throw new AppException(ResultEnum.ILLEGAL_JSON_FORMAT_ERROR);
        }
        String configStr = configNode.toString();
        UserConfigEntity entity = new UserConfigEntity();
        entity.setUserId(id);
        entity.setChannelConfig(configStr);
        Integer count = userConfigDao.updateUserConfig(entity);
        if (count == 0) {
            throw new AppException(ResultEnum.UPDATE_USER_CONFIG_ERROR);
        }
        return configNode;
    }
}
