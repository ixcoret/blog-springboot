package com.ixcoret.blog.config;

import com.ixcoret.blog.security.JsonUsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.HttpSessionEventPublisher;

/**
 * SpringSecurity配置类
 * @author ixcoret
 * @createTime 2021/9/27 18:13
 */
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailHandler;

    /**
     * 密码加密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * HttpSessionEventPublisher类实现了HttpSessionListener接口，
     * 可以监听HttpSession的创建、销毁事件并发布出去
     *
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {
        JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();
        filter.setAuthenticationManager(authenticationManagerBean());
        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailHandler);
        return filter;
    }

    /**
     * 不走Spring Security过滤器链的资源放行，用于放行静态资源
     *
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                //放行swagger、swagger-bootstrap-ui
                "/doc.html",
                "/webjars/**",
                "/swagger-resources/**",
                "/v2/api-docs/**"
        );
    }

    /**
     * 走Spring Security过滤器链
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置登录注销路径
        /*http.formLogin()
                //登录请求地址
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout").permitAll();*/

        // 配置访问控制规则
        http.authorizeRequests()
                // 允许任何人、任何状态访问的权限

                // 其他请求都要登录认证才能访问
                .anyRequest().authenticated()
                .and().formLogin()
                //登录请求地址
                .loginProcessingUrl("/login")
                .and()
                .logout()
                .logoutUrl("/logout").permitAll();

        // 关闭跨站请求防护
        http.csrf().disable();

        // 配置异常处理
        http.exceptionHandling()
                // 未登录或登录过期的处理
                .authenticationEntryPoint(authenticationEntryPoint);

        // 开启会话管理
        http.sessionManagement()
                // 设置会话并发数
                .maximumSessions(1)
                .sessionRegistry(sessionRegistry());

        http.addFilterAt(jsonUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
