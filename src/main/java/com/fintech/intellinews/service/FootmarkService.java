package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.FootmarkDao;
import com.fintech.intellinews.entity.FootmarkEntity;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wanghao
 * create 2017-11-13 13:14
 **/
@Service
public class FootmarkService {

    private FootmarkDao footmarkDao;

    public PageInfo<FootmarkEntity> getUserFootmarks(Long userId,Integer pageNum,Integer pageSize){

        return null;
    }


    @Autowired
    public void setFootmarkDao(FootmarkDao footmarkDao) {
        this.footmarkDao = footmarkDao;
    }
}
