package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.dao.CommentDao;
import com.fintech.intellinews.entity.CommentEntity;
import com.fintech.intellinews.service.ArticleService;
import com.fintech.intellinews.service.CommentService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.CommentVO;
import com.fintech.intellinews.vo.DetailsArticleVO;
import com.fintech.intellinews.vo.SearchArticleVO;
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

    private CommentService commentService;

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "通过关键字搜索文章", notes = "默认返回3条搜索结果，按发布时间、浏览数排序", produces = "application/json")
    public Result<PageInfo<SearchArticleVO>> getArticlesByKeyword(
            @ApiParam(name = "keyword", value = "搜索关键字", required = true)
            @RequestParam(value = "keyword") String keyword,
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(value = "pageSize", defaultValue = "3", required = false) Integer pageSize){
        return ResultUtil.success(articleService.getArticlesByKeyword(keyword,pageNum,pageSize));
    }

    @GetMapping("/{articleId}")
    @ResponseBody
    @ApiOperation(value = "根据文章id获取文章内容详情",notes = "标题、图片、关键字、文章来源、日期、阅读数", produces = "application/json")
    public Result<DetailsArticleVO> getDetailsArticle(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable(value = "articleId") Long id) {
        return ResultUtil.success(articleService.getDetailsArticleById(id));
    }

    @PostMapping("/{articleId}/comments")
    @ResponseBody
    @ApiOperation(value = "根据文章id获取文章的评论", produces = "application/json")
    public Result<PageInfo<CommentVO>> getArticleComments(
            @ApiParam(name = "articleId", value = "文章id", required = true)
            @PathVariable(value = "articleId") Long id,
            @ApiParam(name = "pageNum",value = "搜索页数")
            @RequestParam(name = "pageNum",defaultValue = "1",required = false)Integer pageNum,
            @ApiParam(name = "pageNum",value = "搜索页数")
            @RequestParam(name = "pageSize",defaultValue = "3",required = false)Integer pageSize) {
        return ResultUtil.success(commentService.listArticleComments(id,pageNum,pageSize));
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCommentService(CommentDao commentDao) {
        this.commentService = commentService;
    }
}
