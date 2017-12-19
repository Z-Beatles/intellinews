package com.fintech.intellinews.service;

import com.fintech.intellinews.entity.UserSectionEntity;
import com.fintech.intellinews.vo.UserSectionVO;
import com.github.pagehelper.PageInfo;

/**
 * @author wanghao
 * create 2017-11-13 16:10
 **/
public interface UserSectionService {

    /**
     * 获取条目指定用户的收藏
     *
     * @param userId    用户id
     * @param sectionId 条目id
     * @return 收藏资源
     */
    UserSectionEntity getUserSectionCollect(Long userId, Long sectionId);

    /**
     * 获取用户收藏条目
     *
     * @param userId   用户id
     * @param pageNum  搜索页数
     * @param pageSize 搜索条数
     * @return 收藏列表
     */
    PageInfo<UserSectionVO> getUserSections(Long userId, Integer pageNum, Integer pageSize);

    /**
     * 用户收藏条目
     *
     * @param userId    用户id
     * @param sectionId 条目id
     * @return 新增条目的id
     */
    Long insertUserSection(Long userId, Long sectionId);

    /**
     * 取消收藏条目
     *
     * @return 影响行数
     */
    Long deleteUserSection(Long userId, Long sectionId);
}
