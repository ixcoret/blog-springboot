package com.ixcoret.blog.enums;

import lombok.Getter;

/**
 * 返回码枚举
 *
 * @author ixcoret
 * @date 2021/5/29 14:51
 */
@Getter
public enum ResultCode {
    SUCCESS(20000, "操作成功！"),
    ERROR(40000, "服务器出了点问题！"),
    DATA_NOT_FOUND(40001, "未查到相关数据！"),
    PARAMS_NULL(40002, "参数不能为空！"),
    PARAMS_ERROR(40005, "参数不合法！"),
    NOT_LOGIN(40003, "未登录或登录过期！")
    ;

    /**
     * 返回码
     */
    private Integer code;

    /**
     * 提示信息
     */
    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
