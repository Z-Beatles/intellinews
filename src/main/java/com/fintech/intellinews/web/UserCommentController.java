package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.CommentService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.UserCommentVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-11-30 10:53
 */
@RestController
@Api(tags = "用户评论资源接口")
@RequestMapping("/v1/users/comments")
public class UserCommentController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户发表的所有评论", produces = "application/json")
    public Result<PageInfo<UserCommentVO>> getUserComments(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userService.getUserComments(currentUserId, pageNum, pageSize));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户发表文章评论", produces = "application/json")
    public Result insertComment(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @RequestParam(name = "articleId") Long articleId,
            @ApiParam(name = "content", value = "评论内容", required = true)
            @RequestBody String content) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(commentService.addUserComment(currentUserId, articleId, content));
    }
}
