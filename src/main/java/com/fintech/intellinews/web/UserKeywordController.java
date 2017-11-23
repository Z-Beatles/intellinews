package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.UserKeywordEntity;
import com.fintech.intellinews.service.UserKeywordService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.UserKeywordVO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:06
 **/
@RestController
@RequestMapping("/v1/user/keywords")
public class UserKeywordController {

    private UserService userService;

    private UserKeywordService userKeywordService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取用户偏好关键字", produces = "application/json")
    public Result<List<UserKeywordVO>> getUserHobby(
            @ApiParam(value = "userId",name="用户id",required = true)
            @RequestParam Long userId){
        userService.checkCurrentUser(userId);
        return ResultUtil.success(userKeywordService.getUserKeyWords(userId));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加用户偏好关键字", produces = "application/json")
    public Result<UserKeywordVO> addUserHobby(
            @ApiParam(value = "userId",name="用户id",required = true)
            @RequestParam Long userId,
            @ApiParam(value = "keyword",name="用户偏好关键字",required = true)
            @RequestParam String keyword){
        userService.checkCurrentUser(userId);
        return ResultUtil.success(userKeywordService.addUserKeyword(userId,keyword));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserKeywordService(UserKeywordService userKeywordService) {
        this.userKeywordService = userKeywordService;
    }
}
