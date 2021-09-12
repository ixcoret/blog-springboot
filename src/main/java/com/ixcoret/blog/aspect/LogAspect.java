package com.ixcoret.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.context.SystemContext;
import com.ixcoret.blog.enums.StateEnum;
import com.ixcoret.blog.entity.OperationLog;
import com.ixcoret.blog.service.OperationLogService;
import com.ixcoret.blog.util.IpUtil;
import com.ixcoret.blog.util.SpringUtil;
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
public class LogAspect {

    @Autowired
    private OperationLogService operationLogService;

    @Pointcut("execution(* com.ixcoret.blog.controller.*.*(..)) && @annotation(com.ixcoret.blog.annotation.Log)")
    public void logPointCut(){}

    // 配置切入点表达式
    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        HttpServletRequest request = SpringUtil.getHttpServletRequest();

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
        OperationLog operationLog = SystemContext.getLog();
        operationLog.setIp(ip);
        operationLog.setBrowser(browser);
        operationLog.setUrl(uri);
        operationLog.setController(controller);
        operationLog.setMethod(methodName);
        operationLog.setRequestMethod(requestMethod);
        //sysLog.setParams(params);

        //------------以上为前置通知----------

        // 开始执行controller service mapper的代码
        Object proceed = joinPoint.proceed();
        long end = System.currentTimeMillis();
        long time = end - start;
        log.info("请求耗时：{}毫秒", time);
        operationLog.setTime(time);
        // 后置通知开始
        Integer code = StateEnum.REQUEST_SUCCESS.getCode();
        log.info("请求状态：{}", code);
        operationLog.setStatus(code);
        String result = JSON.toJSONString(proceed);
        log.info("返回结果：{}", result);
        operationLog.setResult(result);
        log.info("创建人：{}", "admin");
        operationLog.setUsername("admin");
        operationLogService.save(operationLog);
        return proceed;
    }

    @AfterThrowing(pointcut = "logPointCut()", throwing = "throwable")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable throwable) {
        OperationLog operationLog = SystemContext.getLog();
        operationLog.setStatus(StateEnum.REQUEST_ERROR.getCode());
        operationLog.setException(throwable.getMessage());
        operationLog.setTime(-1L);
        operationLogService.save(operationLog);
    }
}
