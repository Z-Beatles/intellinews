package com.fintech.intellinews.service;

import com.fintech.intellinews.vo.UserKeywordVO;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:21
 **/
public interface UserKeywordService {

    /**
     * 获取用户前五十偏好关键字
     *
     * @param userId 用户id
     * @return 偏好关键字列表
     */
    List<UserKeywordVO> getUserKeyWords(Long userId);

    /**
     * 添加用户偏好
     *
     * @param userId  用户id
     * @param keyword 偏好关键字
     * @return 成功返回用户添加对象，失败抛出异常
     */
    UserKeywordVO addUserKeyword(Long userId, String keyword);

    /**
     * 更新用户偏好关注度
     *
     * @param userId  用户id
     * @param keyword 偏好关键字
     * @return 成功返回用户添加对象，失败抛出异常
     */
    UserKeywordVO updateHobbyAttention(Long userId, String keyword);
}
