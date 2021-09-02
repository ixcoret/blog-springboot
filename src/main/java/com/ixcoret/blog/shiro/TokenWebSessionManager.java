package com.ixcoret.blog.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author ixcoret
 * @createTime 2021/5/30 14:46
 */
@Component
public class TokenWebSessionManager extends DefaultWebSessionManager {
    // 有个重载的getSessionId方法，这里重写的这个方法更灵活
    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        // 将ServletRequest转成HttpServletRequest
        // shiro.web.util包的WebUtils
        HttpServletRequest httpServletRequest = WebUtils.toHttp(request);
        // 从请求头中获取token
        String token = httpServletRequest.getHeader("Authorization");
        // 如果token存在，就返回token，否则就生成一个
        if (StringUtils.hasText(token)) {
            return token;
        }
        return UUID.randomUUID().toString();
    }
}
