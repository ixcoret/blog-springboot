package com.ixcoret.blog.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.ixcoret.blog.enums.ResultEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author ixcoret
 * @createTime 2021/5/29 14:54
 */
@Data
// 只序列化非null的属性
@JsonInclude(JsonInclude.Include.NON_NULL)
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
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage());
    }

    public static <T> Result<T> success(String message) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), message);
    }

    /**
     * 成功返回结果
     *
     * @param data 返回的数据
     */
    public static <T> Result<T> success(T data) {
        return new Result<>(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), data);
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
        return new Result<>(ResultEnum.SUCCESS.getCode(), message, data);
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
        return new Result<>(ResultEnum.ERROR.getCode(), ResultEnum.ERROR.getMessage());
    }

    /**
     *
     * @param resultEnum 返回码枚举
     */
    public static <T> Result<T> error(ResultEnum resultEnum) {
        return new Result<>(resultEnum.getCode(), resultEnum.getMessage());
    }

    public static <T> Result<T> error(Integer code, String message) {
        return new Result<>(code, message);
    }

}
