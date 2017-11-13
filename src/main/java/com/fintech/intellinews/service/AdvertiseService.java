package com.fintech.intellinews.service;

import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.AdvertiseDao;
import com.fintech.intellinews.entity.AdvertiseEntity;
import com.fintech.intellinews.vo.AdvertiseVO;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 12:20
 **/
@Service
public class AdvertiseService extends BaseService {

    private AdvertiseDao advertiseDao;

    public List<AdvertiseVO> listAds(Integer pageNum, Integer pageSize, Boolean active) {
        AdvertiseEntity entity = new AdvertiseEntity();
        entity.setActive(active);
        PageHelper.startPage(pageNum, pageSize);
        List<AdvertiseEntity> advertiseEntities = advertiseDao.list(entity);

        List<AdvertiseVO> advertiseVOS = new ArrayList<>();
        for (AdvertiseEntity advertiseEntity : advertiseEntities) {
            AdvertiseVO advertiseVO = new AdvertiseVO();
            BeanUtils.copyProperties(advertiseEntity, advertiseVO);
            advertiseVOS.add(advertiseVO);
        }
        return advertiseVOS;
    }

    @Autowired
    public void setAdvertiseDao(AdvertiseDao advertiseDao) {
        this.advertiseDao = advertiseDao;
    }
}
