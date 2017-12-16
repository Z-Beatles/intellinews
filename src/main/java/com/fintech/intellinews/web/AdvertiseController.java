package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.AdvertiseService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.AdvertiseVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:58
 */
@RestController
@Api(tags = "广告资源接口")
@RequestMapping("/v1/advertises")
public class AdvertiseController {

    @Autowired
    private AdvertiseService advertiseService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取首页上架广告", notes = "默认返回4条上架广告", produces = "application/json")
    public Result<List<AdvertiseVO>> listActiveAds(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(value = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(value = "pageSize", defaultValue = "4", required = false) Integer pageSize,
            @ApiParam(name = "active", value = "广告的状态(上架、下架)")
            @RequestParam(value = "active", defaultValue = "true") Boolean active) {
        return ResultUtil.success(advertiseService.listAdvertises(pageNum, pageSize, active));
    }
}
