package com.ixcoret.blog.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author ixcoret
 * @date 2021/6/13 15:48
 */
@Data
public class TokenVO implements Serializable {
    private Serializable token;

    public TokenVO(Serializable token) {
        this.token = token;
    }
}
