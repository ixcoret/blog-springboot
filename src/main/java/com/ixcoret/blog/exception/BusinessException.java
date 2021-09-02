package com.ixcoret.blog.exception;

/**
 * 自定义异常
 * @author ixcoret
 * @createTime 2021/7/31 16:44
 */
public class BusinessException extends RuntimeException {
    private Integer code;

    private String message;

    public BusinessException() {
    }

    public BusinessException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
