package com.ixcoret.blog.shiro;

import com.ixcoret.blog.enums.ResultEnum;
import com.ixcoret.blog.exception.BusinessException;
import com.ixcoret.blog.pojo.entity.SysUser;
import com.ixcoret.blog.service.SysUserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 自定义实现realm，包括认证和授权两部分
 * @author ixcoret
 * @createTime 2021/6/13 10:29
 */
@Component
public class ShiroRealm extends AuthorizingRealm {
    @Autowired
    private SysUserService sysUserService;
    /**
     * 授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();
        SysUser sysUser = sysUserService.getByUsername(username);
        if (sysUser == null) {
            throw new BusinessException(ResultEnum.LOGIN_PARAMS_ERROR.getCode(), ResultEnum.LOGIN_PARAMS_ERROR.getMessage());
        }

        return new SimpleAuthenticationInfo(sysUser, sysUser.getPassword(), this.getName());
    }
}
