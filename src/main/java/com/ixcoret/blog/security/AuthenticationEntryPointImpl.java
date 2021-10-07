package com.ixcoret.blog.security;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.enums.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 当未登录或登录过期访问接口时，自定义返回结果
 *
 * @author ixcoret
 * @createTime 2021/10/4 14:24
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error(ResultCodeEnum.NOT_LOGIN)));
    }
}
