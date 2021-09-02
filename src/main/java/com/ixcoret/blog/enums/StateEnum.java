package com.ixcoret.blog.enums;

import lombok.Getter;

/**
 * 状态枚举
 * @author ixcoret
 * @createTime 2021/6/9 10:13
 */
@Getter
public enum StateEnum {
    /**
     * 逻辑删除状态
     */
    DELETED(1, "已删除"),
    NOT_DELETED(0, "未删除"),

    /**
     * 启用状态
     */
    ENABLED(1, "已启用"),
    NOT_ENABLED(0, "未启用"),

    /**
     * 性别状态
     */
    SEX_MALE(1, "男"),
    SEX_FEMALE(2, "女"),

    /**
     * 请求访问状态枚举
     */
    REQUEST_SUCCESS(1, "请求正常"),
    REQUEST_ERROR(0, "请求异常"),
    ;

    private final Integer code;
    private final String message;

    StateEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
