package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.UserSetupEntity;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserSetupService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wanghao
 * create 2017-10-30 17:59
 **/
@Controller
@RequestMapping("/v1/usersetups")
@Api(tags = "用户配置资源接口")
public class UserSetupController extends BaseController{

    @Autowired
    private UserSetupService userSetupService;

    @RequestMapping("/{userId}")
    @ResponseBody
    @ApiOperation(value = "当前用户(目录)配置", produces = "application/json")
    private Result<UserSetupEntity> currentUserSetup(@PathVariable("userId")Long id){
        if (id < 1){
            return ResultUtil.error(ResultEnum.ACCOUNT_NOTEXIST_ERROR);
        }
        return userSetupService.getCurrentUserSetup(id);
    }

}
