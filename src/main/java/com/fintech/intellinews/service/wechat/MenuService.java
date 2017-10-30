package com.fintech.intellinews.service.wechat;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Constant;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.JacksonUtil;
import com.fintech.intellinews.base.BaseService;
import com.fintech.intellinews.util.HttpUtil;
import org.springframework.stereotype.Service;

/**
 * @author waynechu
 * Created 2017-10-26 14:28
 */
@Service
public class MenuService extends BaseService {

    private static final String ACCESS_TOKEN = "ACCESS_TOKEN";

    /**
     * 创建自定义菜单
     *
     * @param token 凭证
     * @param menu  菜单的json String
     */
    public void createMenu(String token, String menu) {
        String url = Constant.CREATE_MENU_URL.replace(ACCESS_TOKEN, token);
        ObjectNode objectNode = HttpUtil.doPostUrl(url, menu);
        Integer errCode = JacksonUtil.getChildAsInteger(objectNode, "errcode");
        if (errCode != null) {
            switch (errCode) {
                case 0:
                    logger.info("创建自定义菜单成功");
                    break;
                case 40015:
                    logger.warn("自定义菜单失败：不合法的菜单类型");
                    throw new AppException(ResultEnum.ILLEGAL_MENU_TYPES_ERROR);
                case 40016:
                    logger.warn("自定义菜单失败：不合法的按钮个数");
                    throw new AppException(ResultEnum.ILLEGAL_BUTTONS_NUMBER_ERROR);
                default:
                    logger.warn("自定义菜单失败");
                    throw new AppException(ResultEnum.CREATE_MENU_ERROR);
            }
        } else {
            logger.error("创建自定义菜单失败，微信服务器未返回正确结果");
            throw new AppException(ResultEnum.CREATE_MENU_ERROR);
        }
    }

    /**
     * 查询自定义菜单
     *
     * @param token 凭证
     * @return 查询结果
     */
    public ObjectNode queryMenu(String token) {
        String url = Constant.QUERY_MENU_URL.replace(ACCESS_TOKEN, token);
        return HttpUtil.doGetUrl(url);
    }

    /**
     * 删除自定义菜单
     *
     * @param token 凭证
     */
    public void deleteMenu(String token) {
        String url = Constant.DELETE_MENU_URL.replace(ACCESS_TOKEN, token);
        ObjectNode objectNode = HttpUtil.doGetUrl(url);
        Integer errCode = JacksonUtil.getChildAsInteger(objectNode, "errcode");
        if (errCode != null && errCode != 0) {
            throw new AppException(ResultEnum.DELETE_MENU_ERROR);
        }
    }
}
