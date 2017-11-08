package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.SessionService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-23 20:36
 */
@RestController
@Api(tags = "会话管理接口")
@RequestMapping("/v1/session")
public class SessionController extends BaseController {

    private SessionService sessionService;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "登录类型暂时可不填写，登陆成功返回一个名为‘sid’的Cookie，如果设置记住我会返回一个" +
            "名为‘rememberMe’的Cookie用于实现自动登陆", produces = "application/json")
    public Result<String> loginAction(
            @ApiParam(name = "loginType", value = "登陆的类型")
            @RequestParam(required = false) String loginType,
            @ApiParam(name = "account", value = "账号", required = true)
            @RequestParam String account,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password,
            @ApiParam(name = "rememberMe", value = "记住我", defaultValue = "false", required = true)
            @RequestParam boolean rememberMe,
            @ApiParam(name = "host", value = "登陆IP")
            @RequestParam(required = false) String host) {
        return ResultUtil.success(ResultEnum.LOGIN_SUCCEED_INFO, sessionService.doLogin(loginType, account, password,
                rememberMe, host));
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "用户退出", notes = "退出登录‘sid’Cookie将被删除，但保留‘rememberMe’", produces = "application/json")
    public Result logoutAction() {
        return ResultUtil.success(ResultEnum.LOGOUT_SUCCEED_INFO, sessionService.doLogout());
    }

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }
}
