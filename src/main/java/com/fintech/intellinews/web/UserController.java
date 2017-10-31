package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.UserInfoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-26 16:19
 */
@RestController
@Api(tags = "用户资源接口")
@RequestMapping("/v1/users")
public class UserController{

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "", produces = "application/json")
    public Result<UserInfoEntity> register() {
        return null;
    }

    @GetMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户信息", notes = "用于查看指定用户的个人资料", produces = "application/json")
    public Result<UserInfoEntity> getUser(@PathVariable("userId") Long id) {

        return null;
    }

    @GetMapping("/{userId}/like_notes")
    @ResponseBody
    @ApiOperation(value = "获取指定用户的收藏列表", notes = "用于查看指定用户的收藏列表", produces = "application/json")
    public Result<List<ArticleEntity>> collectionArticles(@PathVariable("userId") Long id) {
        return null;
    }

}
