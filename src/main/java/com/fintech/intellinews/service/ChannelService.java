package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.ChannelDao;
import com.fintech.intellinews.entity.ChannelEntity;
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
public class ChannelService extends BaseService {

    private ChannelDao channelDao;

    public List<ChannelVO> listCannels() {
        List<ChannelEntity> channelEntities = channelDao.selectAll();
        List<ChannelVO> channelVOS = new ArrayList<>();
        for (ChannelEntity channelEntity : channelEntities) {
            ChannelVO channelVO = new ChannelVO();
            BeanUtils.copyProperties(channelEntity, channelVO);
            channelVOS.add(channelVO);
        }
        return channelVOS;
    }

    @Autowired
    public void setChannelDao(ChannelDao channelDao) {
        this.channelDao = channelDao;
    }
}
