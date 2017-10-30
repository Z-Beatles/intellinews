package com.fintech.intellinews;

import com.fintech.intellinews.enums.ResultEnum;

/**
 * @author waynechu
 * Created 2017-10-19 11:22
 */
public class AppException extends RuntimeException {

    private Integer errorCode;

    private String errorMessage;

    private Throwable errorCause;

    public AppException(ResultEnum resultEnum) {
        this.errorCode = resultEnum.getCode();
        this.errorMessage = resultEnum.getMessage();
    }

    public AppException(Integer errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public AppException(Integer errorCode, String errorMessage, Throwable errorCause) {
        super(errorMessage, errorCause);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.errorCause = errorCause;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Throwable getErrorCause() {
        return errorCause;
    }

    public void setErrorCause(Throwable errorCause) {
        this.errorCause = errorCause;
    }
}
