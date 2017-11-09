package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
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

    @PutMapping("{userId}/channels")
    @ResponseBody
    @ApiOperation(value = "更新指定用户频道配置", produces = "application/json")
    public Result updateUserChannels(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @PathVariable(name = "userId")Long id){
        return null;
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "注册接口", produces = "application/json")
    public Result<String> register(
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password) {
        return ResultUtil.success(ResultEnum.REGIST_SUCCESS_INFO, userService.insertUser(username, password));
    }

    @GetMapping("{userId}/channels")
    @ResponseBody
    @ApiOperation(value = "获取指定用户频道配置", produces = "application/json")
    public Result getUserChannels(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @PathVariable(name = "userId")Long id){
        return null;
    }

    @GetMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户信息", produces = "application/json")
    public Result<UserInfoEntity> getUser(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId") Long id) {
        return null;
    }

    @PostMapping("/{userId}/footmarks")
    @ResponseBody
    @ApiOperation(value = "添加用户足迹", produces = "application/json")
    public Result createUserFootmarks(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @PathVariable(value = "userId")Long userId,
            @ApiParam(name = "contentId",value = "足迹内容id",required = true)
            @RequestParam(name = "contentId") Long contentId,
            @ApiParam(name = "contentType",value = "足迹内容类型(article、section)",required = true)
            @RequestParam(name = "contentType") String contentType,
            @ApiParam(name = "source",value = "足迹来源(article、home、section)",required = true)
            @RequestParam(name = "source") String source){

        return null;
    }

    @GetMapping("/{userId}/footmarks")
    @ResponseBody
    @ApiOperation(value = "查询用户足迹", produces = "application/json")
    public Result listUserFootmarks(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @PathVariable(value = "userId")Long userId,
            @ApiParam(name = "pageSize",value = "查询条数")
            @RequestParam(name = "pageSize",defaultValue = "10",required = false)Integer pageSize,
            @ApiParam(name = "pageNum",value = "查询页数")
            @RequestParam(name = "pageNum",defaultValue = "1",required = false) Integer pageNum){

        return null;
    }

    @PostMapping("/{userId}/articles/{articleId}")
    @ResponseBody
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result collectArticle(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId")Long userId,
            @ApiParam(name = "articleId", value = "文章id",required = true)
            @PathVariable("articleId")Long articleId){
        return null;
    }

    @PostMapping("/{userId}/articles")
    @ResponseBody
    @ApiOperation(value = "获取用户收藏文章", produces = "application/json")
    public Result getCollectArticle(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId")Long userId){
        return null;
    }

    @PostMapping("/{userId}/sections/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "用户收藏条目", produces = "application/json")
    public Result collectSection(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId")Long userId,
            @ApiParam(name = "sectionId", value = "条目id",required = true)
            @PathVariable("sectionId")Long sectionId){
        return null;
    }

    @GetMapping("/{userId}/sections")
    @ResponseBody
    @ApiOperation(value = "获取用户收藏条目", produces = "application/json")
    public Result getCollectSection(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId")Long userId){
        return null;
    }

    @PostMapping("{userId}/comments")
    @ResponseBody
    @ApiOperation(value = "获取用户发表的所有评论",produces = "application/json")
    public Result getUserComments(
            @ApiParam(name = "userId", value = "用户id",required = true)
            @PathVariable("userId")Long userId){
        return null;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
