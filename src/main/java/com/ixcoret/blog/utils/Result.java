package com.ixcoret.blog.utils;

import com.ixcoret.blog.enums.ResultCode;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ixcoret
 * @date 2021/5/29 14:54
 */
@Data
public class Result<T> implements Serializable {
    private Integer code;

    private String message;

    private T data;

    public Result() {
    }

    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 成功返回结果
     */
    public static <T> Result<T> success() {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage());
    }

    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * 成功返回结果
     *
     * @param code 返回码
     * @param message 提示信息
     */
    public static <T> Result<T> success(Integer code, String message) {
        return new Result<>(code, message);
    }

    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     * @param message 提示信息
     */
    public static <T> Result<T> success(String message, T data) {
        return new Result<>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * 成功返回结果
     *
     * @param code 返回码
     * @param message 提示信息
     * @param data 返回的数据
     */
    public static <T> Result<T> success(Integer code, String message, T data) {
        return new Result<>(code, message, data);
    }

    /**
     * 失败返回结果
     */
    public static <T> Result<T> error() {
        return new Result<>(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMessage());
    }

    /**
     *
     * @param resultCode 返回码枚举
     */
    public static <T> Result<T> error(ResultCode resultCode) {
        return new Result<>(resultCode.getCode(), resultCode.getMessage());
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message);
    }

}
