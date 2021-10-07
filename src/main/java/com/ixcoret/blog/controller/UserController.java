package com.ixcoret.blog.controller;

import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ixcoret
 * @createTime 2021/5/31 14:36
 */
@RestController
@RequestMapping("/user")
@Api(tags = "UserController")
@Slf4j
public class UserController {

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result<SysUser> info() {
        return Result.success();
    }

}
