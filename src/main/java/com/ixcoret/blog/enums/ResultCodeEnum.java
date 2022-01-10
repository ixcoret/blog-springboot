package com.ixcoret.blog.enums;

import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author ixcoret
 * @createTime 2021/5/29 14:51
 */
@Getter
public enum ResultCodeEnum {
    SUCCESS(20000, "操作成功！"),

    ERROR(40000, "服务器异常！"),
    DATA_NOT_FOUND(40001, "未查到相关数据！"),
    PARAMS_ERROR(40002, "参数不正确！"),
    NEED_LOGIN(40003, "未登录或登录过期！"),
    USERNAME_OR_PASSWORD_ERROR(40004, "用户名或密码不正确！"),
    DATA_DUPLICATE(40005, "数据库中已存在相关数据，禁止重复添加！"),
    REQUEST_METHOD_NOT_SUPPORTED(40006, "请求方式不正确！"),
    JSON_FORMAT_ERROR(40007, "缺少json格式的请求体参数或json格式不正确！"),
    ARGUMENT_TYPE_NOT_MATCH(40008, "请求参数数据类型不匹配！"),
    CAPTCHA_ERROR(40009, "验证码不正确！")
    ;

    /**
     * 返回码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;

    ResultCodeEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
