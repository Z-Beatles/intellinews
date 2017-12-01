package com.fintech.intellinews.enums;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author waynechu
 * Created 2017-10-19 11:25
 */
public enum ResultEnum {
    /**
     * 向前端返回的结果
     */
    SYSTEM_ERROR(-1, "系统异常"),
    SUCCESS(0, "succeed"),
    FAILED(1, "failed"),

    ACCOUNT_NOT_EXIST_ERROR(1000, "账号不存在"),
    HAS_LOGGED_IN_INFO(1002, "账号已登录"),
    WRONG_PASSWORD_ERROR(1003, "密码错误"),
    LOGIN_FAILED_ERROR(1004, "登陆失败"),
    REPEAT_LOGIN_ERROR(1005, "账号已在线，请勿重复登陆"),
    NOT_LOGIN_ERROR(1006, "抱歉，您尚未登录"),
    LOGIN_SUCCEED_INFO(1007, "登陆成功"),
    LOGOUT_SUCCEED_INFO(1008, "退出成功"),
    REGISTER_SUCCESS_INFO(1009, "注册成功"),
    ACCOUNT_EXIST_ERROR(1010, "账号已被注册"),
    WITHOUT_PERMISSION_ERROR(1011, "没有权限查看他人信息"),

    SECTION_NOT_EXIST_ERROR(10000, "指定条目不存在"),
    SECTION_NOT_COLLECT_ERROR(10001, "未收藏该条目"),
    COMMENT_NOT_EXIST_ERROR(10002, "指定评论不存在"),
    COMMENT_ARTICLE_FAILED_ERROR(10003, "评论文章失败"),
    COLLECT_ARTICLE_FAILED_ERROR(10004, "收藏文章失败"),
    ARTICLE_NOT_EXIST_ERROR(10005, "指定文章不存在"),
    ARTICLE_NOT_COLLECT_ERROR(10006, "未收藏该文章"),
    COLLECT_SECTION_FAILED_ERROR(10007, "收藏条目失败"),

    CREATE_MENU_ERROR(40000, "自定义菜单失败"),
    DELETE_MENU_ERROR(40001, "删除自定义菜单失败"),
    ILLEGAL_MENU_TYPES_ERROR(40015, "不合法的菜单类型"),
    ILLEGAL_BUTTONS_NUMBER_ERROR(40016, "不合法的按钮个数"),
    UPDATE_USER_CONFIG_ERROR(40017, "更新用户频道配置失败"),
    ILLEGAL_JSON_FORMAT_ERROR(40018, "Json格式不合法");


    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("message", message)
                .toString();
    }
}
