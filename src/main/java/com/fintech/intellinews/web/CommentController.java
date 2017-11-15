package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.service.CommentService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-08 15:42
 **/
@RestController
@Api(tags = "评论资源接口")
@RequestMapping("/v1/comments")
public class CommentController {

    private UserService userService;

    private CommentService commentService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取指定文章下的指定用户评论",notes = "需要验证用户登录")
    public Result<List<CommentEntity>> getComment(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam Long userId,
            @ApiParam(name = "articleId",value = "文章id",required = true)
            @RequestParam Long articleId){
        userService.checkCurrentUser(userId);
        return ResultUtil.success(commentService.getComment(userId,articleId));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
