package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.annotation.LimitIPRequest;
import com.fintech.intellinews.service.ColumnService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.ColumnVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-27 13:55
 */
@RestController
@Api(tags = "专栏资源接口")
@RequestMapping("/v1/columns")
public class ColumnController {

    @Autowired
    private ColumnService columnService;

    @LimitIPRequest(limitCounts = 5, timeSecond = 60, whiteList = {"119.31.210.76"})
    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取专栏列表", notes = "该接口暂未实现", produces = "application/json")
    public Result<PageInfo<ColumnVO>> listColumns(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "3", required = false) Integer pageSize) {
        return ResultUtil.success(columnService.listColumns(pageNum, pageSize));
    }
}
