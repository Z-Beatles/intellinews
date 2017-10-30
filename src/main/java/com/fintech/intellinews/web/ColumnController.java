package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author waynechu
 * Created 2017-10-27 13:55
 */
@RestController
@Api(tags = "专栏资源接口")
@RequestMapping("/v1/columns")
public class ColumnController extends BaseController {

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有专栏", produces = "application/json")
    public Result<String> listColumns() {
        return null;
    }
}
