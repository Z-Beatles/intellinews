package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.annotation.LimitIPRequest;
import com.fintech.intellinews.service.SectionService;
import com.fintech.intellinews.util.RegexUtil;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.DetailsSectionVO;
import com.fintech.intellinews.vo.ListSectionVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author wanghao
 * create 2017-11-08 15:03
 **/
@RestController
@Api(tags = "条目资源接口")
@RequestMapping("/v1/sections")
public class SectionController {

    @Autowired
    private SectionService sectionService;

    @LimitIPRequest(limitCounts = 5, timeSecond = 60, whiteList = {"119.31.210.76"})
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有条目列表", produces = "application/json")
    public Result<PageInfo<ListSectionVO>> getSections(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return ResultUtil.success(sectionService.listSections(pageNum, pageSize));
    }

    @LimitIPRequest(limitCounts = 5, timeSecond = 60, whiteList = {"119.31.210.76"})
    @GetMapping("/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "根据条目id获取条目详情", produces = "application/json")
    public Result<DetailsSectionVO> getSectionById(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable(name = "sectionId") Long sectionId) {
        return ResultUtil.success(sectionService.getSectionById(sectionId));
    }

    @LimitIPRequest(limitCounts = 5, timeSecond = 60, whiteList = {"119.31.210.76"})
    @GetMapping("/search")
    @ResponseBody
    @ApiOperation(value = "按条件搜索条目", notes = "可以按照关键字和首字母搜索，首字母格式为a~z", produces = "application/json")
    public Result<PageInfo> getSectionByKeyword(
            @ApiParam(name = "keyword", value = "查询条件", required = true)
            @RequestParam(name = "keyword") String keyword,
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
        if (RegexUtil.matchStartWith(keyword)) {
            return ResultUtil.success(sectionService.listByStartWith(keyword, pageNum, pageSize));
        }
        return ResultUtil.success(sectionService.listByKeyword(keyword, pageNum, pageSize));
    }

    @LimitIPRequest(limitCounts = 5, timeSecond = 60, whiteList = {"119.31.210.76"})
    @GetMapping("/{sectionId}/atlas")
    @ResponseBody
    @ApiOperation(value = "根据条目id获取指定类型的图谱信息", produces = "application/json")
    public Result<Map<String, Object>> getSectionAtlas(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable(name = "sectionId") Long sectionId,
            @ApiParam(name = "atlasType", value = "图谱类型(article、section)", required = true)
            @RequestParam(name = "atlasType") String atlasType) {
        return ResultUtil.success(sectionService.listBySectionIdAndAtlasType(sectionId, atlasType));
    }
}
