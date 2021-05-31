package com.ixcoret.blog.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ixcoret
 * @date 2021/5/31 14:36
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "SysUserController")
public class SysUserController {
    @GetMapping("/test")
    @ApiOperation("测试Swagger2")
    public String test() {
        return "测试";
    }
}
