package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.UserInfoVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-26 16:19
 */
@RestController
@Api(tags = "用户资源接口")
@RequestMapping("/v1/users")
public class UserController {

    private UserService userService;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "用户注册", notes = "注册成功返回用户id", produces = "application/json")
    public Result<Long> register(
            @ApiParam(name = "nickname", value = "用户昵称")
            @RequestParam(required = false, defaultValue = "还没有想好") String nickname,
            @ApiParam(name = "username", value = "用户名", required = true)
            @RequestParam String username,
            @ApiParam(name = "password", value = "密码", required = true)
            @RequestParam String password) {
        return ResultUtil.success(ResultEnum.REGIST_SUCCESS_INFO, userService.insertUser(nickname, username, password));
    }

    @GetMapping("/me")
    @ResponseBody
    @ApiOperation(value = "获取当前用户的个人信息", produces = "application/json")
    public Result<UserInfoVO> getUserInfo() {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userService.getUserInfo(currentUserId));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
