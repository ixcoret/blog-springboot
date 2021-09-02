package com.ixcoret.blog.enums;

import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author ixcoret
 * @createTime 2021/5/29 14:51
 */
@Getter
public enum ResultEnum {
    SUCCESS(20000, "操作成功！"),

    ERROR(40000, "服务器异常！"),
    DATA_NOT_FOUND(40001, "未查到相关数据！"),
    PARAMS_ERROR(40002, "缺少请求参数或请求参数不正确！"),
    NOT_LOGIN(40003, "未登录或登录过期！"),
    LOGIN_PARAMS_ERROR(40004, "用户名或密码错误！"),
    DATA_EXIST(40005, "数据库中已存在相关数据！"),
    Request_Method_Not_Supported(40006, "请求方式不正确！"),
    ;

    /**
     * 返回码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
