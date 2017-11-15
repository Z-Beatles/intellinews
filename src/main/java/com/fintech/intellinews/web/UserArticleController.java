package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.UserArticleService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghao
 * create 2017-11-15 9:45
 **/
@RestController
@Api(tags = "用户文章收藏资源")
@RequestMapping("/v1/user_articles")
public class UserArticleController {

    private UserService userService;

    private UserArticleService userArticleService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取指定用户指定文章的收藏",notes = "需要验证用户身份",produces = "application/json")
    public Result getUserArticles(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam Long userId,
            @ApiParam(name = "articleId",value = "文章id",required = true)
            @RequestParam Long articleId){
        userService.checkCurrentUser(userId);
        return ResultUtil.success(userArticleService.getUserArticle(userId,articleId));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserArticleService(UserArticleService userArticleService) {
        this.userArticleService = userArticleService;
    }
}
