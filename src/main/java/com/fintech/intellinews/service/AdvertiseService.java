package com.fintech.intellinews.service;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.dao.AdvertiseDao;
import com.fintech.intellinews.entity.AdvertiseEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wanghao
 * create 2017-10-31 12:20
 **/
@Service
public class AdvertiseService extends BaseService {

    @Autowired
    private AdvertiseDao advertiseDao;

    public Result<List<AdvertiseEntity>> selectActive(){
        AdvertiseEntity entity = new AdvertiseEntity();
        entity.setActive(true);
        List<AdvertiseEntity> list = advertiseDao.selectAdvertisements(entity);
        if (list == null || list.size() == 0){
            return ResultUtil.error(ResultEnum.NULL_OBJECT_ERROR);
        }
        return ResultUtil.success(list);
    }

}
