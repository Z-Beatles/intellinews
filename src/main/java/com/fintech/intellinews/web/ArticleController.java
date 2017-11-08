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
    @ApiOperation(value = "通过关键字搜索文章", produces = "application/json")
    public Result<PageInfo<ArticleVO>> getArticlesByKeyword(
            @ApiParam(name = "keyword",value = "搜索关键字",required = true)
            @RequestParam(value = "keyword") String keyword,
            @ApiParam(name = "pageSize",value = "查询条数")
            @RequestParam(value = "pageSize",defaultValue = "10",required = false) Integer limit,
            @ApiParam(name = "pageNum",value = "查询页数")
            @RequestParam(value = "pageNum",defaultValue = "1",required = false) Integer offset,
            @RequestParam(value = "sort",defaultValue = "time") String sort,
            @RequestParam(value = "order",defaultValue = "DESC",required = false) String order) {
        return ResultUtil.success(articleService.getArticlesByKeyword(keyword));
    }
    @GetMapping("/{articleId}")
    @ResponseBody
    @ApiOperation(value = "获取文章内容详情", produces = "application/json")
    public Result<ArticleEntity> getArticle(
            @ApiParam(name = "articleId",value = "文章id",required = true)
            @PathVariable(value = "articleId")Long id){
        return null;
    }

    @PostMapping("/{articleId}/comments")
    @ResponseBody
    @ApiOperation(value = "获取指定文章的评论", produces = "application/json")
    public Result<CommentEntity> getArticleComments(
            @ApiParam(name = "articleId",value = "文章id",required = true)
            @PathVariable(value = "articleId")Long id){
        return null;
    }


    @PostMapping("/{articleId}/like")
    @ResponseBody
    @ApiOperation(value = "用户收藏文章", produces = "application/json")
    public Result<String> likeArticle(
            @ApiParam(name = "articleId",value = "文章id")
            @PathVariable(value = "articleId") String id) {
        return null;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
