package com.ixcoret.blog.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SimpleSession;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author ixcoret
 * @createTime 2021/6/13 10:04
 */
@Component
public class SessionDaoConfig extends EnterpriseCacheSessionDAO {
    @Resource
    private RedisTemplate<Serializable, Session> redisTemplate;

    @Override
    protected Serializable doCreate(Session session) {
        // 走都就是com.ixcoret.blog.config.shiro.TokenWebSessionManager.getSessionId
        Serializable sessionId = this.generateSessionId(session);
        SimpleSession simpleSession = (SimpleSession) session;
        simpleSession.setId(sessionId);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return redisTemplate.boundValueOps(sessionId).get();
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession) {
            ValidatingSession validatingSession = (ValidatingSession) session;
            if (validatingSession.isValid()) {
                redisTemplate.boundValueOps(session.getId()).set(session);
            }else {
                redisTemplate.delete(session.getId());
            }
        }else {
            redisTemplate.boundValueOps(session.getId()).set(session);
        }
    }

    @Override
    protected void doDelete(Session session) {
        redisTemplate.delete(session.getId());
    }
}
