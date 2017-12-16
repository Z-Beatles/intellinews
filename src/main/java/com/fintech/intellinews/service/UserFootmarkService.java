package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.FootmarkVO;
import com.github.pagehelper.PageInfo;

/**
 * @author wanghao
 * create 2017-11-13 13:14
 **/
public interface UserFootmarkService {

    /**
     * 获取用户足迹
     *
     * @param userId   用户id
     * @param pageNum  搜索页数
     * @param pageSize 搜索条数
     * @return 足迹列表
     */
    PageInfo<FootmarkVO> getUserFootmarks(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 添加用户足迹
     *
     * @param userId      用户id
     * @param contentId   足迹内容id
     * @param source      足迹来源
     * @param contentType 足迹内容类型
     * @return 足迹id
     */
    Long addFootmark(Long userId, Long contentId, String source, String contentType);

    /**
     * 删除用户足迹
     *
     * @param footmarkId 足迹id
     * @return 足迹id
     */
    Long deleteUserFootmark(Long footmarkId);
}
