package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.annotation.LimitIPRequest;
import com.fintech.intellinews.service.UserArticleService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.UserCollectVO;
import com.github.pagehelper.PageInfo;
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
@Api(tags = "用户文章收藏资源接口")
@RequestMapping("/v1/users/articles")
public class UserArticleController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserArticleService userArticleService;

    @LimitIPRequest
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户收藏的所有文章", produces = "application/json")
    public Result<PageInfo<UserCollectVO>> getCollectArticle(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userArticleService.getUserCollectArticles(currentUserId, pageNum, pageSize));
    }

    @LimitIPRequest
    @GetMapping("/{articleId}")
    @ResponseBody
    @ApiOperation(value = "获取当前用户收藏的指定文章", notes = "用于判断当前用户是否收藏该文章", produces = "application/json")
    public Result getUserArticles(
            @ApiParam(name = "articleId", value = "文章ID", required = true)
            @PathVariable Long articleId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userArticleService.getUserArticle(currentUserId, articleId));
    }

    @LimitIPRequest
    @ResponseBody
    @PostMapping("/{articleId}")
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result collectArticle(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable("articleId") Long articleId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userArticleService.insertUserArticle(currentUserId, articleId));
    }

    @LimitIPRequest
    @DeleteMapping("/{articleId}")
    @ResponseBody
    @ApiOperation(value = "用户取消收藏文章", produces = "application/json")
    public Result cancelCollectArticle(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable("articleId") Long articleId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userArticleService.deleteUserArticle(currentUserId, articleId));
    }
}
