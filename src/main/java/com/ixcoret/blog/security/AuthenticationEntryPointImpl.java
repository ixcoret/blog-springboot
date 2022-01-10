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
 * 认证异常处理器：处理AuthenticationException异常，注意：这里不是处理认证失败的，而是定义未登录或登录过期的处理逻辑
 *
 * @author ixcoret
 * @createTime 2021/10/4 14:24
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(Result.error(ResultCodeEnum.NEED_LOGIN)));
    }
}
