package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.SectionEntity;
import com.fintech.intellinews.service.SectionService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.ListSectionVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有条目列表", produces = "application/json")
    public Result<PageInfo<ListSectionVO>> getSections(
            @ApiParam(name = "pageNum", value = "搜索页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "搜索条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return ResultUtil.success(sectionService.listSections(pageNum, pageSize));
    }

    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "通过关键字搜索条目", produces = "application/json")
    public Result<PageInfo<ListSectionVO>> getSectionByKeyword(
            @ApiParam(name = "keyword", value = "搜索关键字", required = true)
            @RequestParam(name = "keyword") String keyword,
            @ApiParam(name = "pageNum", value = "搜索页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "搜索条数")
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
        return ResultUtil.success(sectionService.listSectionsByKeyword(keyword,pageNum,pageSize));
    }

    @GetMapping("/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "根据条目id查询条目详情", produces = "application/json")
    public Result<PageInfo<SectionEntity>> getSectionById(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable(name = "sectionId") String sectionId) {
        return null;
    }

    @GetMapping("/{sectionId}/atlas")
    @ResponseBody
    @ApiOperation(value = "根据条目id查询图谱信息", produces = "application/json")
    public Result<PageInfo<SectionEntity>> getSectionAtlas(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable(name = "sectionId") String sectionId) {
        return null;
    }

    @Autowired
    public void setSectionService(SectionService sectionService) {
        this.sectionService = sectionService;
    }
}
