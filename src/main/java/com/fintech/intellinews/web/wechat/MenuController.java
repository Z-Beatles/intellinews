package com.fintech.intellinews.web.wechat;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.base.BaseController;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.model.AccessToken;
import com.fintech.intellinews.service.wechat.MenuService;
import com.fintech.intellinews.service.wechat.SecurityService;
import com.fintech.intellinews.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author waynechu
 * Created 2017-10-26 14:21
 */
@Controller
@Api(value = "/v1/wechat/menu", tags = "微信公众号自定义菜单接口")
@RequestMapping("/v1/wechat/menu")
public class MenuController extends BaseController {

    private SecurityService securityService;

    private MenuService menuService;

    @PostMapping
    @ResponseBody
    @ApiOperation(value = "创建自定义菜单", notes = "自定义菜单最多包括3个一级菜单，每个一级菜单最多" +
            "包含5个二级菜单，菜单字段定义请查看自定义菜单接口页的说明", produces = "application/json")
    public Result createMenu(
            @ApiParam(name = "menuStr", value = "菜单Json数据", required = true)
            @RequestBody String menuStr) {
        AccessToken accessToken = securityService.getAccessToken();
        menuService.createMenu(accessToken.getToken(), menuStr);
        return ResultUtil.success(ResultEnum.SUCCESS);
    }

    @GetMapping
    @ResponseBody
    @ApiOperation(value = "查询菜单详情", notes = "本自定义菜单查询接口可以获取默认菜单和全部个性化" +
            "菜单信息", produces = "application/json")
    public Result listMenus() {
        AccessToken accessToken = securityService.getAccessToken();
        return ResultUtil.success(ResultEnum.SUCCESS, menuService.queryMenu(accessToken.getToken()));
    }

    @DeleteMapping
    @ResponseBody
    @ApiOperation(value = "删除自定义菜单", notes = "本接口删除当前使用的自定义菜单，请注意在个性化" +
            "菜单时，调用此接口会删除默认菜单及全部个性化菜单", produces = "application/json")
    public Result deleteMenus() {
        AccessToken accessToken = securityService.getAccessToken();
        menuService.deleteMenu(accessToken.getToken());
        return ResultUtil.success(ResultEnum.SUCCESS);
    }


    @Autowired
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Autowired
    public void setMenuService(MenuService menuService) {
        this.menuService = menuService;
    }
}
