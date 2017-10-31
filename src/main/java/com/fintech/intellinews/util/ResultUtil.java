package com.fintech.intellinews.util;

import com.fintech.intellinews.Result;
import com.fintech.intellinews.enums.ResultEnum;

/**
 * @author waynechu
 * Created 2017-10-19 11:20
 */
public class ResultUtil {

    private ResultUtil() {
    }

    public static <T> Result<T> success(ResultEnum resultEnum, T t) {
        Result<T> result = new Result<>();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(t);
        return result;
    }

    public static <T> Result<T> success(T t) {
        return success(ResultEnum.SUCCESS, t);
    }

    public static Result success() {
        return success(ResultEnum.SUCCESS);
    }

    public static <T> Result<T> error(ResultEnum resultEnum, T t) {
        return success(resultEnum, t);
    }

    public static Result error(ResultEnum resultEnum) {
        return error(resultEnum, null);
    }

    public static Result error() {
        return error(ResultEnum.FAILED);
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
