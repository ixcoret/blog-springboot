package com.ixcoret.blog.controller;

import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.dto.LoginDTO;
import com.ixcoret.blog.enums.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登录、注销登录接口测试
 *     注意：不会执行到这里，请求在认证过滤器执行认证逻辑时就会被拦截处理
 *
 * @author ixcoret
 * @createTime 2021/10/8 16:39
 */
@RestController
@Api(tags = "LoginController")
public class LoginController {

    @ApiOperation("登录测试")
    @PostMapping("/login")
    public Result<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return Result.error(ResultCodeEnum.NEED_LOGIN.getCode(), "尚未登录，请先登录！");
    }

    @ApiOperation("注销登录测试")
    @GetMapping("/logout")
    public Result<?> logout() {
        return Result.error(ResultCodeEnum.NEED_LOGIN.getCode(), "尚未登录，请先登录！");
    }

}
