package com.ixcoret.blog.aop;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.context.SystemContext;
import com.ixcoret.blog.enums.StateEnum;
import com.ixcoret.blog.pojo.entity.SysLog;
import com.ixcoret.blog.service.SysLogService;
import com.ixcoret.blog.utils.IpUtil;
import com.ixcoret.blog.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author ixcoret
 * @createTime 2021/6/7 12:45
 */
@Component
@Aspect
@Slf4j
public class SysLogAop {

    @Autowired
    private SysLogService sysLogService;

    @Pointcut("execution(* com.ixcoret.blog.controller.*.*(..)) && @annotation(com.ixcoret.blog.annotation.Log)")
    public void logPointCut(){}

    // 配置切入点表达式
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HttpServletRequest request = SpringContextUtil.getHttpServletRequest();

        String ip = IpUtil.getIpAddr(request);
        String browser = request.getHeader("User-Agent");
        String uri = request.getRequestURI();
        String controller = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String requestMethod = request.getMethod();
        Object[] params = joinPoint.getArgs();
        log.info("ip地址为：{}", ip);
        log.info("浏览器信息：{}", browser);
        log.info("请求的url：{}", uri);
        log.info("执行的controller：{}", controller);
        log.info("执行的controller方法：{}", methodName);

        log.info("请求方式：{}", requestMethod);
        log.info("请求参数：{}", params);

        long start = System.currentTimeMillis();
        SysLog sysLog = SystemContext.getLog();
        sysLog.setIp(ip);
        sysLog.setBrowser(browser);
        sysLog.setUrl(uri);
        sysLog.setController(controller);
        sysLog.setMethod(methodName);
        sysLog.setRequestMethod(requestMethod);
        //sysLog.setParams(params);


        //------------以上为前置通知----------

        // 开始执行controller service mapper的代码
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("请求耗时：{}毫秒", time);
        sysLog.setTime(time);
        // 后置通知开始
        Integer code = StateEnum.REQUEST_SUCCESS.getCode();
        log.info("请求状态：{}", code);
        sysLog.setStatus(code);
        String result = JSON.toJSONString(proceed);
        log.info("返回结果：{}", result);
        sysLog.setResult(result);
        log.info("创建人：{}", "admin");
        sysLog.setUsername("admin");
        sysLogService.save(sysLog);
        return proceed;
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        SysLog sysLog = SystemContext.getLog();
        sysLog.setStatus(StateEnum.REQUEST_ERROR.getCode());
        sysLog.setException(throwable.getMessage());
        sysLog.setTime(-1L);
        sysLogService.save(sysLog);
    }
}
