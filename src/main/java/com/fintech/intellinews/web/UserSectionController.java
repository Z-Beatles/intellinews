package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.entity.UserSectionEntity;
import com.fintech.intellinews.service.UserSectionService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author wanghao
 * create 2017-11-15 10:01
 **/
@RestController
@Api(tags = "用户条目收藏资源")
@RequestMapping("/v1/user_sections")
public class UserSectionController {

    private UserService userService;

    private UserSectionService userSectionService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取条目指定用户的收藏",notes = "需要用户身份验证",produces = "application/json")
    public Result<UserSectionEntity> getUserSection(
            @ApiParam(name = "userId",value = "用户id",required = true)
            @RequestParam Long userId,
            @ApiParam(name = "sectionId",value = "条目id",required = true)
            @RequestParam Long sectionId){

        userService.checkCurrentUser(userId);
        return ResultUtil.success(userSectionService.getUserSectionCollect(userId,sectionId));
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserSectionService(UserSectionService userSectionService) {
        this.userSectionService = userSectionService;
    }
}
