package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.dao.UserConfigDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserConfigService{

    private ObjectMapper objectMapper;

    private UserConfigDao userConfigDao;

    @Transactional
    public ArrayNode getUserChannelConfig(Long id) {
        String config = userConfigDao.getUserChannelConfig(id);
        if (config == null) {
            String defaultConfig = Constant.DEFAULT_USER_CHANNEL_CONFIG;
            userConfigDao.insertUserConfig(id, defaultConfig, new Date());
            return JacksonUtil.toArrayNodeFromString(objectMapper, defaultConfig);
        }
        return JacksonUtil.toArrayNodeFromString(objectMapper, config);
    }

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
        Integer flag = userConfigDao.update(entity);
        if (flag < 1) {
            throw new AppException(ResultEnum.UPDATE_USER_CONFIG_ERROR);
        }
        return configNode;
    }

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setUserConfigDao(UserConfigDao userConfigDao) {
        this.userConfigDao = userConfigDao;
    }

}
