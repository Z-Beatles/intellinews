package com.fintech.intellinews.web;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.service.UserFootmarkService;
import com.fintech.intellinews.service.UserService;
import com.fintech.intellinews.util.ResultUtil;
import com.fintech.intellinews.vo.FootmarkVO;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-11-30 11:07
 */
@RestController
@Api(tags = "用户足迹资源接口")
@RequestMapping("/v1/users/footmarks")
public class UserFootmarkController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserFootmarkService footmarkService;

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "获取当前用户的所有浏览足迹", produces = "application/json")
    public Result<PageInfo<FootmarkVO>> listUserFootmarks(
            @ApiParam(name = "pageNum", value = "查询页数")
            @RequestParam(name = "pageNum", defaultValue = "1", required = false) Integer pageNum,
            @ApiParam(name = "pageSize", value = "查询条数")
            @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(footmarkService.getUserFootmarks(currentUserId, pageNum, pageSize));
    }

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "添加当前用户浏览足迹", produces = "application/json")
    public Result createUserFootmarks(
            @ApiParam(name = "contentId", value = "足迹内容id", required = true)
            @RequestParam(name = "contentId") Long contentId,
            @ApiParam(name = "contentType", value = "足迹内容类型(article、section)", required = true)
            @RequestParam(name = "contentType") String contentType,
            @ApiParam(name = "source", value = "足迹来源(home、article、section)", required = true)
            @RequestParam(name = "source") String source) {
        Long currentUserId = userService.getCurrentUserId();
        return ResultUtil.success(footmarkService.addFootmark(currentUserId, contentId, source, contentType));
    }

    @DeleteMapping("/{footmarkId}")
    @ResponseBody
    @ApiOperation(value = "删除当前用户指定的浏览足迹", produces = "application/json")
    public Result<Long> deleteUserFootmark(
            @ApiParam(name = "footmarkId", value = "足迹id", required = true)
            @PathVariable Long footmarkId) {
        userService.getCurrentUserId();
        return ResultUtil.success(footmarkService.deleteUserFootmark(footmarkId));
    }
}
