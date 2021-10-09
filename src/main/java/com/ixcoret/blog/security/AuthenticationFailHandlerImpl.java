package com.ixcoret.blog.security;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.enums.ResultCodeEnum;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 认证失败处理器：如用户名或密码不正确
 *
 * @author ixcoret
 * @createTime 2021/10/5 15:18
 */
@Component
public class AuthenticationFailHandlerImpl implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(JSON.toJSONString(Result.error(ResultCodeEnum.USERNAME_OR_PASSWORD_ERROR)));
    }
}
