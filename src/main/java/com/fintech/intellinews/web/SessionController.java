package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.entity.UserLoginEntity;
import com.fintech.intellinews.shiro.LoginAuthenticationToken;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-23 20:36
 */
@RestController
@Api(tags = "会话管理接口")
@RequestMapping("/v1/session")
public class SessionController extends BaseController {

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户登录", notes = "登陆成功返回一个名为‘sid’的Cookie，如果设置记住我会返回一个" +
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
        Subject currentUser = SecurityUtils.getSubject();
        if (!currentUser.isAuthenticated()) {
            LoginAuthenticationToken token = new LoginAuthenticationToken(loginType, account, password, rememberMe,
                    host);
            token.setRememberMe(rememberMe);
            try {
                currentUser.login(token);
                logger.info("账号{}登陆成功", account);
                return ResultUtil.success(ResultEnum.LOGIN_SUCCEED_INFO, account);
            } catch (UnknownAccountException e) {
                logger.warn("账号{}不存在", account);
                return ResultUtil.error(ResultEnum.ACCOUNT_NOTEXIST_ERROR, account);
            } catch (LockedAccountException e) {
                logger.warn("账号{}被锁定", account);
                return ResultUtil.error(ResultEnum.ACCOUNT_LOCKED_ERROR, account);
            } catch (DisabledAccountException e) {
                logger.warn("账号{}已禁用", account);
                return ResultUtil.error(ResultEnum.ACCOUNT_DISABLED_ERROR, account);
            } catch (IncorrectCredentialsException e) {
                logger.warn("密码错误，账号：{}", account);
                return ResultUtil.error(ResultEnum.WRONG_PASSWORD_ERROR, account);
            } catch (AuthenticationException e) {
                logger.warn("登录失败", e);
                return ResultUtil.error(ResultEnum.LOGIN_FAILED_ERROR, account);
            } catch (Exception e) {
                logger.error("系统异常", e);
            }
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR);
        }
        logger.warn("账号重复登陆，账号：{}", account);
        return ResultUtil.error(ResultEnum.REPEAT_LOGIN_ERROR, account);
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "用户退出", notes = "退出登录‘sid’Cookie将被删除，但保留‘rememberMe’", produces = "application/json")
    public Result logoutAction() {
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            UserLoginEntity principal = (UserLoginEntity) currentUser.getPrincipal();
            String account = principal.getUsername();
            try {
                currentUser.logout();
                logger.info("用户{}退出系统", account);
                return ResultUtil.success(ResultEnum.LOGOUT_SUCCEED_INFO, account);
            } catch (Exception e) {
                logger.error("系统异常", e);
            }
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR);
        }
        return ResultUtil.error(ResultEnum.NOT_LOGIN_ERROR);
    }
}
