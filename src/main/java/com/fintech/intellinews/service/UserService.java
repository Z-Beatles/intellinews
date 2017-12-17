package com.fintech.intellinews.service;

import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.vo.UserCommentVO;
import com.fintech.intellinews.vo.UserInfoVO;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
public interface UserService {

    /**
     * 根据帐号获取用户登录信息
     *
     * @param account 帐号
     * @return 用户登录信息
     */
    UserLoginEntity getByAccount(String account);

    /**
     * 添加用户
     *
     * @param nickname 用户昵称
     * @param username 用户名
     * @param password 密码
     * @return 用户id
     */
    Long insertUser(String nickname, String username, String password);

    /**
     * 获取当前登录的用户id
     *
     * @return 用户id
     */
    Long getCurrentUserId();

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    UserInfoVO getUserInfo(Long id);

    /**
     * 获取用户评论
     *
     * @param userId   用户id
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 查询评论列表
     */
    PageInfo<UserCommentVO> getUserComments(Long userId, Integer pageNum, Integer pageSize);
}
