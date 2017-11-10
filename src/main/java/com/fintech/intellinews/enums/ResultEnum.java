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

    ACCOUNT_NOTEXIST_ERROR(1000, "账号不存在"),
    HAS_LOGGED_IN_INFO(1002, "账号已登录"),
    WRONG_PASSWORD_ERROR(1003, "密码错误"),
    LOGIN_FAILED_ERROR(1004, "登陆失败"),
    REPEAT_LOGIN_ERROR(1005, "账号已在线，请勿重复登陆"),
    NOT_LOGIN_ERROR(1006, "抱歉，您尚未登录"),
    LOGIN_SUCCEED_INFO(1007, "登陆成功"),
    LOGOUT_SUCCEED_INFO(1008, "退出成功"),
    REGIST_SUCCESS_INFO(1009, "注册成功"),
    ACCOUNT_EXIST_ERROR(1010, "账号已被注册"),
    WITHOUT_PERMISSION_ERROR(1011, "没有权限查看他人信息"),

    CREATE_MENU_ERROR(40000, "自定义菜单失败"),
    DELETE_MENU_ERROR(40001, "删除自定义菜单失败"),
    ILLEGAL_MENU_TYPES_ERROR(40015, "不合法的菜单类型"),
    ILLEGAL_BUTTONS_NUMBER_ERROR(40016, "不合法的按钮个数");

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
