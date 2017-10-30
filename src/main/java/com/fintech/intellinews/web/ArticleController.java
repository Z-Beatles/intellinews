package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.ArticleEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 11:53
 */

@RestController
@Api(tags = "文章资源接口")
@RequestMapping("/v1/articles")
public class ArticleController {

    @GetMapping("/{categoryName}")
    @ResponseBody
    @ApiOperation(value = "按照目录查询文章列表详情", notes = "目录有待商量", produces = "application/json")
    public Result<String> getArticlesByCategory(@PathVariable("categoryName") String categoryName) {
        return null;
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "按照关键字查询文章列表详情", notes = "", produces = "application/json")
    public Result<List<ArticleEntity>> getArticlesByKeyword(@RequestParam("keyword") String keyword) {
        return null;
    }

    @PostMapping("/{articleId}/like")
    @ResponseBody
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result<String> likeArticle(@PathVariable(value = "articleId") String id) {
        return null;
    }

}
