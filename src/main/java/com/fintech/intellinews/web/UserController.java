package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.UserInfoEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-26 16:19
 */
@RestController
@Api(tags = "用户资源接口")
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "注册接口", produces = "application/json")
    public Result<String> register(
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password) {
        return ResultUtil.success(ResultEnum.REGIST_SUCCESS_INFO, userService.register(username, password));
    }

    @GetMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户信息", produces = "application/json")
    public Result<UserInfoEntity> getUser(
            @PathVariable("userId") Long id) {
        return null;
    }

    @PostMapping("/{userId}/articles/{articleId}")
    @ResponseBody
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result collectArticle(
            @PathVariable("userId")Long uid,
            @PathVariable("articleId")Long aid){
        return null;
    }

    @PostMapping("/{userId}/articles")
    @ResponseBody
    @ApiOperation(value = "获取用户收藏文章", produces = "application/json")
    public Result collectArticle(
            @PathVariable("userId")Long uid){
        return null;
    }

    @PostMapping("/{userId}/sections/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "用户收藏条目", produces = "application/json")
    public Result collectSection(
            @PathVariable("userId")Long uid,
            @PathVariable("sectionId")Long sid){
        return null;
    }

    @GetMapping("/{userId}/sections/")
    @ResponseBody
    @ApiOperation(value = "获取用户收藏条目", produces = "application/json")
    public Result getCollectSection(
            @PathVariable("userId")Long uid){
        return null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
