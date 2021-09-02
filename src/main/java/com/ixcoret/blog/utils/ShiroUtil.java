package com.ixcoret.blog.utils;

import com.ixcoret.blog.pojo.entity.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;

/**
 * @author ixcoret
 * @createTime 2021/6/13 18:48
 */
public class ShiroUtil {
    public static SysUser getLoginUser() {
        Session session = SecurityUtils.getSubject().getSession();
        SimplePrincipalCollection principalCollection = (SimplePrincipalCollection) session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        if (principalCollection == null) {
            return null;
        }
        return (SysUser) principalCollection.getPrimaryPrincipal();
    }

    public static String getToken() {
        return (String) SecurityUtils.getSubject().getSession().getId();
    }
}
