package com.fintech.intellinews.handle;

import com.fintech.intellinews.AppException;
import com.fintech.intellinews.Result;
import com.fintech.intellinews.enums.ResultEnum;
import com.fintech.intellinews.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author waynechu
 * Created 2017-10-19 11:21
 */
@ControllerAdvice
public class ExceptionHandle {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandle.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e) {
        if (e instanceof AppException) {
            AppException appException = (AppException) e;
            return ResultUtil.error(appException.getErrorCode(), appException.getErrorMessage());
        } else {
            logger.error("【系统异常】 {}", e);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR);
        }
    }
}
