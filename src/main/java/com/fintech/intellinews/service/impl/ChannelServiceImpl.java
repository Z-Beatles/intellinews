package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.dao.ChannelDao;
import com.fintech.intellinews.entity.ChannelEntity;
import com.fintech.intellinews.service.ChannelService;
import com.fintech.intellinews.vo.ChannelVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waynechu
 * Created 2017-11-06 16:48
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private ChannelDao channelDao;

    /**
     * 获取所有频道列表
     *
     * @return 频道列表
     */
    @Override
    public List<ChannelVO> listChannels() {
        List<ChannelEntity> channelEntities = channelDao.listAll();
        List<ChannelVO> channelVOS = new ArrayList<>();
        for (ChannelEntity channelEntity : channelEntities) {
            ChannelVO channelVO = new ChannelVO();
            BeanUtils.copyProperties(channelEntity, channelVO);
            channelVOS.add(channelVO);
        }
        return channelVOS;
    }
}
