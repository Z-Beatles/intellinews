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

    @SuppressWarnings("unchecked")
    public static Result success(ResultEnum resultEnum, Object object) {
        Result result = new Result();
        result.setCode(resultEnum.getCode());
        result.setMessage(resultEnum.getMessage());
        result.setData(object);
        return result;
    }

    public static Result success(ResultEnum resultEnum) {
        return success(resultEnum, null);
    }

    public static Result success() {
        return success(ResultEnum.SUCCESS);
    }

    public static Result error(ResultEnum resultEnum, Object object) {
        return success(resultEnum,object);
    }

    public static Result error(ResultEnum resultEnum) {
        return error(resultEnum, null);
    }

    public static Result error() {
        return error(ResultEnum.FAIL);
    }

    public static Result error(Integer code, String message) {
        Result result = new Result();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }
}
