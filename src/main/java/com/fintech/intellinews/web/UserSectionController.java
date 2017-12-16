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
 * @author waynechu
 * Created 2017-11-30 10:49
 */
@RestController
@Api(tags = "用户条目收藏资源接口")
@RequestMapping("/v1/users/sections")
public class UserSectionController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserSectionService userSectionService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户收藏的所有条目", produces = "application/json")
    public Result getCollectSection(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userSectionService.getUserSections(currentUserId, pageNum, pageSize));
    }

    @GetMapping("/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "获取当前用户收藏的指定条目", notes = "用于判断用户是否收藏该条目", produces = "application/json")
    public Result<UserSectionEntity> getUserSection(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable Long sectionId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userSectionService.getUserSectionCollect(currentUserId, sectionId));
    }

    @PostMapping("/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "用户收藏条目", produces = "application/json")
    public Result collectSection(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable("sectionId") Long sectionId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userSectionService.insertUserSection(currentUserId, sectionId));
    }

    @DeleteMapping("/{sectionId}")
    @ResponseBody
    @ApiOperation(value = "取消收藏条目", produces = "application/json")
    public Result cancelCollectSection(
            @ApiParam(name = "sectionId", value = "条目id", required = true)
            @PathVariable("sectionId") Long sectionId) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(userSectionService.deleteUserSection(currentUserId, sectionId));
    }
}
