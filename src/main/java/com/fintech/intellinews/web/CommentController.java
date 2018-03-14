package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.annotation.LimitIPRequest;
import com.fintech.intellinews.service.CommentService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghao
 * create 2017-11-08 15:42
 **/
@RestController
@Api(tags = "评论资源接口")
@RequestMapping("/v1/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @LimitIPRequest
    @PutMapping("{commentId}/like")
    @ResponseBody
    @ApiOperation(value = "为指定评论点赞", produces = "application/json")
    public Result likeComment(
            @ApiParam(name = "commentId", value = "评论id", required = true)
            @PathVariable(name = "commentId") Long commentId) {
        commentService.updateComment(commentId, 1);
        return ResultUtil.success();
    }

    @LimitIPRequest
    @PutMapping("{commentId}/dislike")
    @ResponseBody
    @ApiOperation(value = "为指定评论点踩", produces = "application/json")
    public Result dislikeComment(
            @ApiParam(name = "commentId", value = "评论id", required = true)
            @PathVariable(name = "commentId") Long commentId) {
        commentService.updateComment(commentId, -1);
        return ResultUtil.success();
    }
}
