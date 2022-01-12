package com.ixcoret.blog.aspect;

import com.alibaba.fastjson.JSON;
import com.ixcoret.blog.context.HttpRequestHolder;
import com.ixcoret.blog.entity.OperationLog;
import com.ixcoret.blog.enums.StateEnum;
import com.ixcoret.blog.service.OperationLogService;
import com.ixcoret.blog.util.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 日志切面类
 *
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

    @Around("logPointCut()")
    public Object logAround(ProceedingJoinPoint pjp) throws Throwable {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        HttpServletRequest request = HttpRequestHolder.getHttpServletRequest();

        String ip = IpUtil.getIpAddr(request);
        String browser = request.getHeader("User-Agent");
        String uri = request.getRequestURI();
        String controller = signature.getDeclaringTypeName();
        String methodName = signature.getName();
        String requestMethod = request.getMethod();
        Object[] params = pjp.getArgs();
        log.info("ip地址为：{}", ip);
        log.info("浏览器信息：{}", browser);
        log.info("请求的url：{}", uri);
        log.info("执行的controller：{}", controller);
        log.info("执行的controller方法：{}", methodName);

        log.info("请求方式：{}", requestMethod);
        log.info("请求参数：{}", params);

        long start = System.currentTimeMillis();
        OperationLog operationLog = new OperationLog();
        operationLog.setIp(ip);
        operationLog.setBrowser(browser);
        operationLog.setUrl(uri);
        operationLog.setController(controller);
        operationLog.setMethod(methodName);
        operationLog.setRequestMethod(requestMethod);

        Object retVal;
        try {
            retVal = pjp.proceed();
            long end = System.currentTimeMillis();
            long time = end - start;
            log.info("请求耗时：{}毫秒", time);
            operationLog.setTime(time);
            Integer code = StateEnum.REQUEST_SUCCESS.getCode();
            log.info("请求状态：{}", code);
            operationLog.setStatus(code);
            String result = JSON.toJSONString(retVal);
            log.info("返回结果：{}", result);
            operationLog.setResult(result);
        } catch (Exception e) {
            operationLog.setStatus(StateEnum.REQUEST_ERROR.getCode());
            operationLog.setException(e.getMessage());
            operationLog.setTime(-1L);
            // 再将异常抛出，避免事务失效
            throw e;
        } finally {
            log.info("创建人：{}", "admin");
            operationLog.setUsername("admin");
            // TODO: 异步记录日志
            operationLogService.save(operationLog);
        }

        return retVal;
    }
}
