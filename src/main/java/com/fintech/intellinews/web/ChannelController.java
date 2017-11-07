package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.ChannelEntity;
import com.fintech.intellinews.entity.ColumnEntity;
import com.fintech.intellinews.entity.UserConfigEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.ChannelService;
import com.fintech.intellinews.service.UserConfigService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author waynechu
 * Created 2017-10-27 13:50
 */
@RestController
@Api(tags = "频道资源接口")
@RequestMapping("/v1/channel")
public class ChannelController extends BaseController {

    private ChannelService channelService;
    private UserConfigService userConfigService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取所有频道", produces = "application/json")
    public Result<List<ChannelEntity>> listChanels() {
        return ResultUtil.success(channelService.listChanels());
    }

    @GetMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "获取指定用户的频道", produces = "application/json")
    public Result<UserConfigEntity> getUserChannelMenu(@PathVariable(value = "userId") Long id) {
        return ResultUtil.success(userConfigService.getUserConfig(id));
    }

    @Autowired
    public void setChannelService(ChannelService channelService) {
        this.channelService = channelService;
    }

    @Autowired
    public void setUserConfigService(UserConfigService userConfigService) {
        this.userConfigService = userConfigService;
    }
}
