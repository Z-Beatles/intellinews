package com.fintech.intellinews.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.dao.UserConfigDao;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.util.JacksonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-07 10:11
 **/
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserConfigService {

    private ObjectMapper objectMapper;

    private UserConfigDao userConfigDao;

    public PageInfo<List<UserConfigEntity>> listConfig(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum < 1) {
            pageNum = 1;
        }
        PageHelper.startPage(pageNum, pageSize);
        List<UserConfigEntity> list = userConfigDao.getSortEntity();
        PageInfo<List<UserConfigEntity>> pageInfo = new PageInfo<>();
        return pageInfo;
    }

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

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Autowired
    public void setUserConfigDao(UserConfigDao userConfigDao) {
        this.userConfigDao = userConfigDao;
    }
}
