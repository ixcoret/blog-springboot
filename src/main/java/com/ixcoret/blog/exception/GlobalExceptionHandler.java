package com.ixcoret.blog.exception;

import com.ixcoret.blog.api.Result;
import com.ixcoret.blog.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Objects;

/**
 * @author ixcoret
 * @createTime 2021/5/29 17:31
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 处理自定义异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 参数验证失败：未通过 bean validation验证，如 @Valid、@Validated等
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 获取第一个验证失败的消息
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return Result.error(ResultCodeEnum.PARAMS_ERROR.getCode(), message);
    }

    /**
     * json参数解析失败：缺少json格式请求体参数或json格式不正确
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultCodeEnum.JSON_FORMAT_ERROR);
    }

    /**
     * 请求方式不支持，例如，A接口的请求方式为 POST，结果以 GET方式请求，导致不匹配
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultCodeEnum.REQUEST_METHOD_NOT_SUPPORTED);
    }

    /**
     * POST、PUT请求的content-type不正确：
     *      例如，服务端期望的请求头Content-Type: application/json 客户端以Content-Type: application/x-www-form-urlencoded发送请求
     *      此时异常消息：Content type 'application/x-www-form-urlencoded;charset=UTF-8' not supported
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultCodeEnum.PARAMS_ERROR.getCode(), e.getMessage());
    }

    /**
     * 请求参数数据类型不匹配，例如 /delete/1  以String类型"1"传参
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultCodeEnum.ARGUMENT_TYPE_NOT_MATCH);
    }

    /**
     * 缺少请求参数
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultCodeEnum.PARAMS_ERROR.getCode(), e.getMessage());
    }

    /**
     * 违反数据库主键或唯一约束：插入或更新数据时，不希望的数据重复
     *
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.info(e.getMessage());
        return Result.error(ResultCodeEnum.DATA_DUPLICATE);
    }

    /*
                    SpringMVC自定义异常对应的status code
            Exception                               HTTP Status Code
    ConversionNotSupportedException             500 (Internal Server Error)
    HttpMessageNotWritableException             500 (Internal Server Error)
    HttpMediaTypeNotSupportedException          415 (Unsupported Media Type)
    HttpMediaTypeNotAcceptableException         406 (Not Acceptable)
    HttpRequestMethodNotSupportedException      405 (Method Not Allowed)
    NoSuchRequestHandlingMethodException        404 (Not Found)
    TypeMismatchException                       400 (Bad Request) : 尝试设置bean属性时因类型不匹配引发的异常
    HttpMessageNotReadableException             400 (Bad Request)
    MissingServletRequestParameterException     400 (Bad Request)*/

    // BindException
    // ValidationException
    // ConstraintViolationException
    // MethodArgumentTypeMismatchException：参数类型不匹配
    // MissingServletRequestParameterException：缺少请求参数
    // HttpRequestMethodNotSupportedException：请求方式不支持。例如，A接口的请求方式为POST，结果以GET方式请求，导致不匹配
    // HttpMessageNotReadableException：json参数解析失败
    // HttpMediaTypeNotSupportedException：POST、PUT请求的content-type不正确
    // MaxUploadSizeExceededException：文件上传大小超限
    // NoHandlerFoundException：找不到处理器
    // IllegalArgumentException：参数非法
    // RuntimeException
    // Exception

    /**
     * 其他异常，兜底
     * @param e
     * @return
     */
    /*@ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.info(e.getMessage());
        return Result.error(ResultEnum.ERROR);
    }*/
}
