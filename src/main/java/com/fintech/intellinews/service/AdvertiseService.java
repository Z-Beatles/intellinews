package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.AdvertiseVO;

import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 12:20
 **/
public interface AdvertiseService {

    /**
     * 获取广告列表
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @param active   是否上架
     * @return 广告列表
     */
    List<AdvertiseVO> listAdvertises(Integer pageNum, Integer pageSize, Boolean active);
}
