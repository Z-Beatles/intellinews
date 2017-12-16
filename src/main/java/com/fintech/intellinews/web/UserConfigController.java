package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.UserConfigService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-11-30 10:42
 */
@RestController
@Api(tags = "用户频道配置资源接口")
@RequestMapping("/v1/users/channels")
public class UserConfigController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserConfigService userConfigService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户的频道配置", produces = "application/json")
    public Result getUserChannels() {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userConfigService.getUserChannelConfig(currentUserId));
    }

    @PutMapping
    @ResponseBody
    @ApiOperation(value = "更新当前用户的频道配置", produces = "application/json")
    public Result updateUserChannels(
            @ApiParam(name = "config", value = "用户Json配置", required = true)
            @RequestBody String config) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userConfigService.updateUserChannelConfig(currentUserId, config));
    }
}
