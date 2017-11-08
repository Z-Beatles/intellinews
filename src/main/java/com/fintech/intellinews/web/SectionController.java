package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.service.SectionService;
import com.fintech.intellinews.util.ResultUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghao
 * create 2017-11-08 15:03
 **/
@RestController
@Api(tags = "条目资源接口")
@RequestMapping("/v1/sections")
public class SectionController {

    private SectionService sectionService;

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "通过关键字搜索条目", produces = "application/json")
    public Result<PageInfo<SectionEntity>> getArticlesByKeyword(
            @RequestParam(value = "keyword",required = true) String keyword,
            @RequestParam(value = "limit",defaultValue = "10") Integer limit,
            @RequestParam(value = "offset",defaultValue = "1") Integer offset) {
        return null;
    }



}
