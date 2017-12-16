package com.fintech.intellinews.service.impl;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.dao.ArticleDao;
import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.dao.UserInfoDao;
import com.fintech.intellinews.dao.UserLoginDao;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.entity.UserInfoEntity;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.properties.AppProperties;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.DateUtil;
import com.fintech.intellinews.util.RegexUtil;
import com.fintech.intellinews.vo.UserCommentVO;
import com.fintech.intellinews.vo.UserInfoVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AppProperties appProperties;

    @Autowired
    private UserInfoDao userInfoDao;

    @Autowired
    private UserLoginDao userLoginDao;

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private ArticleDao articleDao;

    /**
     * 根据帐号获取用户登录信息
     *
     * @param account 帐号
     * @return 用户登录信息
     */
    @Override
    public UserLoginEntity getByAccount(String account) {
        UserInfoEntity userInfo = new UserInfoEntity();
        if (RegexUtil.matchMobile(account)) {
            userInfo.setPhone(account);
        } else if (RegexUtil.matchEmail(account)) {
            userInfo.setEmail(account);
        } else {
            userInfo.setUsername(account);
        }
        List<UserInfoEntity> userInfoEntities = userInfoDao.listUserInfos(userInfo);
        if (userInfoEntities.isEmpty()) {
            return null;
        }
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(userInfoEntities.get(0).getUsername());
        return userLoginDao.listUserLogins(userLoginEntity).get(0);
    }

    /**
     * 添加用户
     *
     * @param nickname 用户昵称
     * @param username 用户名
     * @param password 密码
     * @return 用户id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long insertUser(String nickname, String username, String password) {
        UserLoginEntity entity = new UserLoginEntity();
        entity.setUsername(username);
        List<UserLoginEntity> entities = userLoginDao.listUserLogins(entity);
        if (!entities.isEmpty()) {
            throw new AppException(ResultEnum.ACCOUNT_EXIST_ERROR);
        }
        RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
        String salt = randomNumberGenerator.nextBytes().toHex();
        String algorithmName = appProperties.getAlgorithmName();
        int hashIterations = appProperties.getHashIterations();
        String hexPassword = new SimpleHash(algorithmName, password, salt, hashIterations).toHex();

        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(username);
        userLoginEntity.setNickname(nickname);
        userLoginEntity.setAvatar(Constant.DEFAULT_USER_AVATAR);
        userLoginEntity.setPasswordHash(hexPassword);
        userLoginEntity.setPasswordSalt(salt);
        userLoginEntity.setPasswordAlgo(algorithmName);
        userLoginEntity.setPasswordIteration(hashIterations);
        userLoginDao.insertUserLogin(userLoginEntity);

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(userLoginEntity.getId());
        userInfoEntity.setUsername(username);
        userInfoDao.insertUserInfo(userInfoEntity);
        return userLoginEntity.getId();
    }

    /**
     * 获取当前登录的用户id
     *
     * @return 用户id
     */
    @Override
    public Long getCurrentUserId() {
        Subject currentUser = SecurityUtils.getSubject();
        UserLoginEntity principal = (UserLoginEntity) currentUser.getPrincipal();
        if (principal == null) {
            throw new AppException(ResultEnum.NOT_LOGIN_ERROR);
        } else {
            return principal.getId();
        }
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    @Override
    public UserInfoVO getUserInfo(Long id) {
        UserLoginEntity userLoginEntity = userLoginDao.getUserLoginById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userLoginEntity, userInfoVO);
        return userInfoVO;
    }

    /**
     * 获取用户评论
     *
     * @param userId   用户id
     * @param pageNum  分页页数
     * @param pageSize 分页条数
     * @return 查询评论列表
     */
    @Override
    @SuppressWarnings("unchecked")
    public PageInfo<UserCommentVO> getUserComments(Long userId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<CommentEntity> userComments = commentDao.listUserComments(userId);
        if (userComments.isEmpty()) {
            return new PageInfo(userComments);
        }
        List<Long> articleIdList = new ArrayList<>();
        for (CommentEntity entity : userComments) {
            articleIdList.add(entity.getArticleId());
        }
        Map<Long, ArticleEntity> articlesMap = articleDao.mapArticlesByIds(articleIdList);
        List<UserCommentVO> resultList = new ArrayList<>();
        UserCommentVO userCommentVO;
        ArticleEntity articleEntity;
        String dateDesc;
        for (CommentEntity entity : userComments) {
            userCommentVO = new UserCommentVO();
            BeanUtils.copyProperties(entity, userCommentVO);
            articleEntity = articlesMap.get(entity.getArticleId());
            if (articleEntity == null) {
                continue;
            }
            userCommentVO.setTitle(articleEntity.getTitle());
            userCommentVO.setThumbnail(articleEntity.getThumbnail());
            dateDesc = DateUtil.toDetailTimeString(articleEntity.getGmtCreate());
            userCommentVO.setDate(dateDesc);
            resultList.add(userCommentVO);
        }
        PageInfo pageInfo = new PageInfo(userComments);
        pageInfo.setList(resultList);
        return pageInfo;
    }
}
