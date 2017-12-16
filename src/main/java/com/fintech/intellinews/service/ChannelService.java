package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.ChannelVO;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-11-06 16:48
 */
public interface ChannelService {

    /**
     * 获取所有频道列表
     *
     * @return 频道列表
     */
    List<ChannelVO> listChannels();
}
