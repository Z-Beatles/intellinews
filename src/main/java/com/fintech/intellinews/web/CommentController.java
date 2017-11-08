package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghao
 * create 2017-11-08 15:42
 **/
@RestController
@Api(tags = "评论资源接口")
@RequestMapping("/v1/comments")
public class CommentController {

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "创建用户评论文章", produces = "application/json")
    public Result insertComment(
            @RequestParam(value = "userId",required = true)Long uid,
            @RequestParam(value = "articleId",required = true)Long aid){
        return null;
    }

}
