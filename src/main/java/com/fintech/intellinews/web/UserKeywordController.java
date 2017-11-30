package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.UserKeywordService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.UserKeywordVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author wanghao
 * create 2017-11-23 11:06
 **/
@RestController
@Api(tags = "用户偏好关键字资源")
@RequestMapping("/v1/users/keywords")
public class UserKeywordController {

    private UserService userService;

    private UserKeywordService userKeywordService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户的所有偏好关键字", produces = "application/json")
    public Result<List<UserKeywordVO>> getUserHobby() {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userKeywordService.getUserKeyWords(currentUserId));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加用户偏好关键字", produces = "application/json")
    public Result<UserKeywordVO> addUserHobby(
            @ApiParam(name = "keyword", value = "用户偏好关键字", required = true)
            @RequestParam String keyword) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userKeywordService.addUserKeyword(currentUserId, keyword));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserKeywordService(UserKeywordService userKeywordService) {
        this.userKeywordService = userKeywordService;
    }
}
