package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.AdvertiseEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:58
 */
@RestController
@Api(tags = "广告资源接口")
@RequestMapping("/v1/ads")
public class AdvertiseController extends BaseController {

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有广告资源", produces = "application/json")
    public Result<List<AdvertiseEntity>> listAds() {
        return null;
    }

    @GetMapping("/active")
    @ResponseBody
    @ApiOperation(value = "获取首页上架广告", produces = "application/json")
    public Result<String> listActiveAds() {
        return null;
    }

}
