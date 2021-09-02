package com.ixcoret.blog.controller;

import com.ixcoret.blog.enums.ResultEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.pojo.entity.SysUser;
import com.ixcoret.blog.pojo.vo.TokenVO;
import com.ixcoret.blog.pojo.vo.form.LoginForm;
import com.ixcoret.blog.utils.Result;
import com.ixcoret.blog.utils.ShiroUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.io.Serializable;

/**
 * @author ixcoret
 * @createTime 2021/5/31 14:36
 */
@RestController
@RequestMapping("/sys/user")
@Api(tags = "SysUserController")
@Slf4j
public class SysUserController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result<TokenVO> login(@Valid @RequestBody LoginForm loginForm) {
        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken authenticationToken = new UsernamePasswordToken(loginForm.getUsername(), loginForm.getPassword());
        // 登录失败
        try {
            subject.login(authenticationToken);
        }catch (Exception e) {
            throw new BusinessException(ResultEnum.LOGIN_PARAMS_ERROR.getCode(), ResultEnum.LOGIN_PARAMS_ERROR.getMessage());
        }

        // 登录成功
        Serializable token = subject.getSession().getId();
        return Result.success(new TokenVO(token));
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Result<SysUser> info() {
        SysUser sysUser = ShiroUtil.getLoginUser();
        if (sysUser == null) {
            throw new RuntimeException("当前用户未登录！");
        }
        sysUser.setPassword(null);
        return Result.success(sysUser);
    }

    @GetMapping("/logout")
    @ApiOperation("退出登录")
    public Result logout() {
        // 解决bug：退出登录删除redis中的token后，前端携带token仍能执行需认证的操作
        // shiro缓存中会存储token，让shiro删除token
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        /*String token = ShiroUtil.getToken();
        redisTemplate.delete(token);*/
        return Result.success("退出成功！");
    }
}
