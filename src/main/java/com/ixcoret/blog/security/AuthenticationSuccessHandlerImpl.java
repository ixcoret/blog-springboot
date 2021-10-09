package com.ixcoret.blog.security;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.api.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证成功处理器：给用户一个提示或返回认证信息（用户信息）
 *
 * @author ixcoret
 * @createTime 2021/10/5 16:36
 */
@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        authentication.getName();
        response.getWriter().write(JSON.toJSONString(Result.success("登录成功")));
    }
}
