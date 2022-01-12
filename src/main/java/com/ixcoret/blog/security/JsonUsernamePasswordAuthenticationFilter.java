package com.ixcoret.blog.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * 自定义的登录过滤器，处理Json格式的用户名密码
 *     也可以不在过滤器中处理：在controller中写登录接口，并在SpringSecurity配置中放行该接口
 *
 * @author ixcoret
 * @createTime 2021/10/7 19:37
 */
public class JsonUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }
        // 判断请求体是否是json数据
        if (request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_VALUE)
                || request.getContentType().equalsIgnoreCase(MediaType.APPLICATION_JSON_UTF8_VALUE)) {
            // 封装JSON数据
            try (ServletInputStream inputStream = request.getInputStream()) {
                // 从数据流中读取JSON数据并封装到map中
                Map<String, String> authenticationBean = new ObjectMapper().readValue(inputStream, Map.class);
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(authenticationBean.get("username"),
                        authenticationBean.get("password"));
                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return super.attemptAuthentication(request, response);
    }
}
