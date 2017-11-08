package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.dao.AdvertiseDao;
import com.fintech.intellinews.entity.AdvertiseEntity;
import com.fintech.intellinews.service.AdvertiseService;
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
@RequestMapping("/v1/ads")
public class AdvertiseController extends BaseController {
    @Autowired
    private AdvertiseService advertiseService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取首页上架广告", produces = "application/json")
    public Result<List<AdvertiseEntity>> listActiveAds(
            @ApiParam(name = "active", value = "广告的状态(上架、下架)")
            @RequestParam(value = "active",defaultValue = "true")Boolean active) {
        return advertiseService.selectActive();
    }

}
