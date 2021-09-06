package com.ixcoret.blog.exception;

import com.ixcoret.blog.enums.ResultEnum;
import com.ixcoret.blog.utils.Result;
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
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public Result handleBusinessException(BusinessException e) {
        return Result.error(e.getCode(), e.getMessage());
    }

    /**
     * 处理违反数据库主键、唯一键约束导致的异常：插入或更新数据时，不希望的数据重复，例如用户名、博客分类
     * @return
     * TODO：获取引发该异常的具体字段（中文），给用户以更友好的提示
     */
    @ExceptionHandler(DuplicateKeyException.class)
    public Result handleDuplicateKeyException(DuplicateKeyException e) {
        log.info(e.getMessage());
        return Result.error(ResultEnum.DATA_EXIST.getCode(), ResultEnum.DATA_EXIST.getMessage());
    }

    /**
     * 处理参数验证失败：未通过bean validation验证：@Valid、@Validated
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        // 获取第一个验证失败的消息
        String message = Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage();
        return Result.error(ResultEnum.PARAMS_ERROR.getCode(), message);
    }

    /**
     * 处理请求方式不支持异常：A接口的请求方式为GET，结果以POST方式请求，导致不匹配
     * @param e
     * @return
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultEnum.Request_Method_Not_Supported);
    }

    /**
     * 处理json参数解析失败
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultEnum.JSON_FORMAT_ERROR);
    }

    /**
     * 处理缺少请求参数
     * @param e
     * @return
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultEnum.PARAMS_ERROR.getCode(), e.getMessage());
    }

    /**
     * 处理POST、PUT请求的content-type不正确
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        log.info("异常信息：{}", e.getMessage());
        return Result.error(ResultEnum.PARAMS_ERROR.getCode(), e.getMessage());
    }

    /*            SpringMVC自定义异常对应的status code
            Exception                           HTTP Status Code
    ConversionNotSupportedException         500 (Internal Server Error)
    HttpMessageNotWritableException         500 (Internal Server Error)
    HttpMediaTypeNotSupportedException      415 (Unsupported Media Type)
    HttpMediaTypeNotAcceptableException     406 (Not Acceptable)
    HttpRequestMethodNotSupportedException  405 (Method Not Allowed)
    NoSuchRequestHandlingMethodException    404 (Not Found)
    TypeMismatchException                   400 (Bad Request) : 尝试设置 bean 属性时因类型不匹配引发的异常
    HttpMessageNotReadableException         400 (Bad Request)
    MissingServletRequestParameterException 400 (Bad Request)*/

    // BindException
    // ValidationException
    // ConstraintViolationException
    // MethodArgumentTypeMismatchException：参数类型不匹配
    // MissingServletRequestParameterException：缺少请求参数
    // HttpRequestMethodNotSupportedException：请求方式不支持。例如：A 接口的请求方式为 GET，结果以POST方式请求，导致不匹配
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
