package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:29
 */
@RestController
@Api(tags = "关键字资源接口")
@RequestMapping("/v1/keywords")
public class KeywordController extends BaseController {

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取搜索关键字", produces = "application/json")
    public Result<List<String>> listHotKeywords(
            @ApiParam(name = "type",value = "搜索类型(hot)")
            @RequestParam(name = "type",defaultValue = "hot")String type,
            @ApiParam(name = "pageSize",value = "搜索条数")
            @RequestParam(name = "pageSize",defaultValue = "10",required = false)Integer pageSize,
            @ApiParam(name = "pageNum",value = "搜索页数")
            @RequestParam(name = "pageNum",defaultValue = "1",required = false)Integer pageNum) {
        return null;
    }
}
