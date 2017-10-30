package com.fintech.intellinews;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * @author waynechu
 * Created 2017-10-19 11:22
 */
public class Result<T> {

    private Integer code;

    private String message;

    private T data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("code", code)
                .append("message", message)
                .append("data", data)
                .toString();
    }
}
