package com.fintech.intellinews.service;

import com.fintech.intellinews.dao.FootmarkDao;
import com.fintech.intellinews.entity.FootmarkEntity;
import com.fintech.intellinews.vo.FootmarkVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @author wanghao
 * create 2017-11-13 13:14
 **/
@Service
public class FootmarkService {

    private FootmarkDao footmarkDao;

    public PageInfo<FootmarkVO> getUserFootmarks(Long userId, Integer pageNum, Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<FootmarkEntity> footmarks = footmarkDao.getUserFootmarks(userId);
        if (footmarks==null||footmarks.size()==0){
            return new PageInfo(footmarks);
        }
        List<FootmarkVO> footmarkVOS = new ArrayList<>();
        FootmarkVO footmarkVO;
        for (FootmarkEntity entity : footmarks){
            footmarkVO = new FootmarkVO();
            BeanUtils.copyProperties(entity,footmarkVO);
            exchangeDate(footmarkVO,entity);
            footmarkVOS.add(footmarkVO);
        }
        PageInfo pageInfo = new PageInfo(footmarks);
        pageInfo.setList(footmarkVOS);
        return pageInfo;
    }

    public static void main(String[] args){
        System.out.println(Calendar.getInstance().get(Calendar.DATE));
    }

    private void exchangeDate(FootmarkVO footmarkVO,FootmarkEntity entity){
        Calendar date = Calendar.getInstance();
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DATE);
        date.setTime(entity.getGmtCreate());
        footmarkVO.setYear(String.valueOf(date.get(Calendar.YEAR)));
        if (year == date.get(Calendar.YEAR)&&month == date.get(Calendar.MONTH)){
            switch (day-date.get(Calendar.DATE)){
                case 0:
                    footmarkVO.setDate("今天");
                    break;
                case 1:
                    footmarkVO.setDate("昨天");
                    break;
                case 2:
                    footmarkVO.setDate("前天");
                    break;
                default:
                    footmarkVO.setDate(month+"-"+date.get(Calendar.DATE));
                    break;
            }
        }else{
            footmarkVO.setDate(date.get(Calendar.MONTH)+"-"+date.get(Calendar.DATE));
        }
        footmarkVO.setTime(date.get(Calendar.HOUR)+"-"+date.get(Calendar.MINUTE));
    }

    @Autowired
    public void setFootMarkDao(FootmarkDao footmarkDao) {
        this.footmarkDao = footmarkDao;
    }
}
