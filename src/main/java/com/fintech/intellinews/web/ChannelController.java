package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.*;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.ArticleService;
import com.fintech.intellinews.service.ChannelService;
import com.fintech.intellinews.service.UserConfigService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.ArticleVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:50
 */
@RestController
@Api(tags = "频道资源接口")
@RequestMapping("/v1/channels")
public class ChannelController extends BaseController {

    private ChannelService channelService;
    private ArticleService articleService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有频道", produces = "application/json")
    public Result<List<ChannelEntity>> listChannels() {
        return ResultUtil.success(channelService.listChanels());
    }

    @GetMapping("/{channelId}/articles")
    @ResponseBody
    @ApiOperation(value = "获取指定频道的文章", produces = "application/json")
    public Result<PageInfo<ArticleVO>> getArticlesByChannelId(
            @ApiParam(name = "channelId", value = "频道id", required = true)
            @PathVariable("channelId") Long channelId,
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum",defaultValue = "1") Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize",defaultValue = "10") Integer pageSize) {
        return ResultUtil.success(articleService.getArticlesByChannelId(channelId, pageNum, pageSize));
    }

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }
}
