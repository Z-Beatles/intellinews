package com.fintech.intellinews.service;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.config.AppProperties;
import com.fintech.intellinews.dao.*;
import com.fintech.intellinews.entity.*;
import com.fintech.intellinews.enums.ResultEnum;
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
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author waynechu
 * Created 2017-10-23 14:04
 */
@Service
@Scope(scopeName = ConfigurableBeanFactory.SCOPE_SINGLETON, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserService extends BaseService {

    private AppProperties appProperties;

    private UserInfoDao userInfoDao;

    private UserLoginDao userLoginDao;

    private UserSectionDao userSectionDao;

    private CommentDao commentDao;

    private ArticleDao articleDao;

    public UserLoginEntity getByAccount(String account) {
        UserInfoEntity userInfo = new UserInfoEntity();
        if (RegexUtil.matchMobile(account)) {
            userInfo.setPhone(account);
        } else if (RegexUtil.matchEmail(account)) {
            userInfo.setEmail(account);
        } else {
            userInfo.setUsername(account);
        }
        List<UserInfoEntity> userInfoEntities = userInfoDao.list(userInfo);
        if (userInfoEntities.isEmpty()) {
            return null;
        }
        UserLoginEntity userLoginEntity = new UserLoginEntity();
        userLoginEntity.setUsername(userInfoEntities.get(0).getUsername());
        return userLoginDao.list(userLoginEntity).get(0);
    }

    @Transactional
    public Long insertUser(String nickname, String username, String password) {
        UserLoginEntity entity = new UserLoginEntity();
        entity.setUsername(username);
        List<UserLoginEntity> entities = userLoginDao.list(entity);
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
        userLoginEntity.setGmtCreate(new Date());
        userLoginDao.insert(userLoginEntity);

        UserInfoEntity userInfoEntity = new UserInfoEntity();
        userInfoEntity.setUserId(userLoginEntity.getId());
        userInfoEntity.setUsername(username);
        userInfoEntity.setGmtCreate(new Date());
        userInfoDao.insert(userInfoEntity);
        return userLoginEntity.getId();
    }

    public void checkCurrentUser(Long id) {
        Subject currentUser = SecurityUtils.getSubject();
        UserLoginEntity principal = (UserLoginEntity) currentUser.getPrincipal();
        if (!principal.getId().equals(id)) {
            throw new AppException(ResultEnum.WITHOUT_PERMISSION_ERROR);
        }
    }

    public UserInfoVO getUserInfo(Long id) {
        UserLoginEntity userLoginEntity = userLoginDao.getById(id);
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userLoginEntity, userInfoVO);
        return userInfoVO;
    }

    /**
     * 用户收藏条目
     * @param userId 用户id
     * @param sectionId 条目id
     * @return 收藏记录的条目id
     */
    public Long collectSection(Long userId,Long sectionId){
        UserSectionEntity userSection = new UserSectionEntity();
        userSectionDao.insert(userSection);
        return userSection.getId();
    }

    /**
     * 获取用户评论
     * @param userId 用户id
     * @param pageNum 分页页数
     * @param pageSize 分页条数
     * @return 查询评论列表
     */
    @SuppressWarnings("unchecked")
    public PageInfo<UserCommentVO> getUserComments(Long userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<CommentEntity> userComments = commentDao.listUserComments(userId);
        if (userComments.size()==0){
            return new PageInfo(userComments);
        }
        List<Long> articleIdList = new ArrayList<>();
        for (CommentEntity entity : userComments){
            articleIdList.add(entity.getArticleId());
        }
        Map<Long,ArticleEntity> articlesMap = articleDao.mapArticlesByIds(articleIdList);
        List<UserCommentVO> resultList = new ArrayList<>();
        UserCommentVO userCommentVO ;
        ArticleEntity articleEntity;
        String dateDesc;
        for (CommentEntity entity : userComments){
            userCommentVO = new UserCommentVO();
            BeanUtils.copyProperties(entity,userCommentVO);
            articleEntity = articlesMap.get(entity.getArticleId());
            if (articleEntity == null){
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

    @Autowired
    public void setAppProperties(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Autowired
    public void setUserInfoDao(UserInfoDao userInfoDao) {
        this.userInfoDao = userInfoDao;
    }

    @Autowired
    public void setUserLoginDao(UserLoginDao userLoginDao) {
        this.userLoginDao = userLoginDao;
    }

    @Autowired
    public void setUserSectionDao(UserSectionDao userSectionDao) {
        this.userSectionDao = userSectionDao;
    }

    @Autowired
    public void setCommentDao(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    @Autowired
    public void setArticleDao(ArticleDao articleDao) {
        this.articleDao = articleDao;
    }

}
