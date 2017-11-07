package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.dto.ArticleDTO;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.service.ArticleService;
import com.fintech.intellinews.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

    private ArticleService articleService;

    @GetMapping("/{channelId}/channel")
    @ResponseBody
    @ApiOperation(value = "根据频道id查询文章列表详情", notes = "", produces = "application/json")
    public Result<PageInfo<ArticleDTO>> getArticlesByChannelId(
            @ApiParam(name = "channelId", value = "频道id", required = true)
            @PathVariable("channelId") Long channelId,
            @ApiParam(name = "pageNum", value = "查询页数", required = true)
            @RequestParam int pageNum,
            @ApiParam(name = "pageSize", value = "查询条数", required = true)
            @RequestParam int pageSize) {
        return ResultUtil.success(articleService.getArticlesByChannelId(channelId, pageNum, pageSize));
    }

    @GetMapping("/{keyword}/keyword")
    @ResponseBody
    @ApiOperation(value = "根据关键字查询文章列表详情", notes = "", produces = "application/json")
    public Result<PageInfo<ArticleDTO>> getArticlesByKeyword(@PathVariable("keyword") String keyword) {
        return ResultUtil.success(articleService.getArticlesByKeyword(keyword));
    }

    @PostMapping("/{articleId}/like")
    @ResponseBody
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result<String> likeArticle(@PathVariable(value = "articleId") String id) {
        return null;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
