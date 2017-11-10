package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.ArticleEntity;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.vo.ArticleVO;
import com.fintech.intellinews.service.ArticleService;
import com.fintech.intellinews.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-27 11:53
 */

@RestController
@Api(tags = "文章资源接口")
@RequestMapping("/v1/articles")
public class ArticleController {

    private ArticleService articleService;

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "通过关键字搜索文章", notes = "默认返回3条搜索结果，按发布时间、浏览数排序", produces = "application/json")
    public Result<PageInfo<ArticleVO>> getArticlesByKeyword(
            @ApiParam(name = "keyword", value = "搜索关键字", required = true)
            @RequestParam(value = "keyword") String keyword,
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer offset,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer limit,
            @ApiParam(name = "sort", value = "排序字段")
            @RequestParam(value = "sort", defaultValue = "time",required = false) String sort,
            @ApiParam(name = "order", value = "DESC,ASC")
            @RequestParam(value = "order", defaultValue = "DESC", required = false) String order) {
        return ResultUtil.success(articleService.getArticlesByKeyword(keyword));
    }

    @GetMapping("/{articleId}")
    @ResponseBody
    @ApiOperation(value = "根据文章id获取文章内容详情",notes = "标题、图片、关键字、文章来源、日期、阅读数", produces = "application/json")
    public Result<ArticleEntity> getArticle(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable(value = "articleId") Long id) {
        return null;
    }

    @PostMapping("/{articleId}/comments")
    @ResponseBody
    @ApiOperation(value = "根据文章id获取文章的评论", produces = "application/json")
    public Result<CommentEntity> getArticleComments(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable(value = "articleId") Long id,
            @ApiParam(name = "pageNum",value = "搜索页数")
            @RequestParam(name = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @ApiParam(name = "pageNum",value = "搜索页数")
            @RequestParam(name = "pageSize",defaultValue = "3",required = false)Integer pageSize) {
        return null;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
