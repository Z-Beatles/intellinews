package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.vo.ColumnVO;
import com.fintech.intellinews.service.ColumnService;
import com.fintech.intellinews.util.ResultUtil;
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
public class ColumnController extends BaseController {

    private ColumnService columnService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取专栏列表", produces = "application/json")
    public Result<PageInfo<ColumnVO>> listColumns(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum",defaultValue = "1",required = false) int pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize",defaultValue = "3",required = false) int pageSize) {
        return ResultUtil.success(columnService.listColumns(pageNum,pageSize));
    }

    @Autowired
    public void setColumnService(ColumnService columnService) {
        this.columnService = columnService;
    }
}
