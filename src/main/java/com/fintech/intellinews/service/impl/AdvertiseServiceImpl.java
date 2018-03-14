package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.dao.AdvertiseDao;
import com.fintech.intellinews.entity.AdvertiseEntity;
import com.fintech.intellinews.service.AdvertiseService;
import com.fintech.intellinews.vo.AdvertiseVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 12:20
 **/
@Service
public class AdvertiseServiceImpl implements AdvertiseService {

    @Autowired
    private AdvertiseDao advertiseDao;

    /**
     * 获取广告列表
     *
     * @param pageNum  页数
     * @param pageSize 页大小
     * @param active   是否上架
     * @return 广告列表
     */
    @Cacheable(cacheNames = "advertises", key = "T(String).valueOf(#pageNum).concat('-').concat(#pageSize).concat('-').concat(#active)")
    @Override
    public List<AdvertiseVO> listAdvertises(Integer pageNum, Integer pageSize, Boolean active) {
        AdvertiseEntity entity = new AdvertiseEntity();
        entity.setActive(active);
        PageHelper.startPage(pageNum, pageSize);
        List<AdvertiseEntity> advertiseEntities = advertiseDao.listAdvertises(entity);

        List<AdvertiseVO> advertiseVOS = new ArrayList<>();
        for (AdvertiseEntity advertiseEntity : advertiseEntities) {
            AdvertiseVO advertiseVO = new AdvertiseVO();
            BeanUtils.copyProperties(advertiseEntity, advertiseVO);
            advertiseVOS.add(advertiseVO);
        }
        return advertiseVOS;
    }
}
