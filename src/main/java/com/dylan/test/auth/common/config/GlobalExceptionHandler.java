package com.dylan.test.auth.common.config;

import com.dylan.test.auth.common.consts.ActionResult;
import com.dylan.test.auth.common.consts.ExceptionConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedRuntimeException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionConstant.EncryptionException.class)
    public ActionResult handleException(ExceptionConstant.EncryptionException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("url'{}' fail", requestURI, e);
        return ActionResult.failure(e.getMessage());
    }

    @ExceptionHandler(ExceptionConstant.UserInfoParseException.class)
    public ActionResult handleException(ExceptionConstant.UserInfoParseException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("url'{}' fail", requestURI, e);
        return ActionResult.failure(e.getMessage());
    }

    @ExceptionHandler(ExceptionConstant.UserInfoValidateException.class)
    public ActionResult handleException(ExceptionConstant.UserInfoValidateException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("url'{}' fail", requestURI, e);
        return ActionResult.failure(e.getMessage());
    }

    /**
     * system
     */
    @ExceptionHandler(Exception.class)
    public ActionResult handleException(Exception e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("url'{}' fail.", requestURI, e);
        return ActionResult.failure("An unknown exception occurred, please contact the administrator.");
    }

    @ExceptionHandler(NestedRuntimeException.class)
    public ActionResult handleException(NestedRuntimeException e, HttpServletRequest request)
    {
        String requestURI = request.getRequestURI();
        log.error("url'{}' fail.", requestURI, e);
        return ActionResult.failure("Value transmission error");
    }


}
