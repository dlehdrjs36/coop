package com.projectteam.coop.api.exhandler.advice;

import com.projectteam.coop.api.ApiResult;
import com.projectteam.coop.exception.*;
import com.projectteam.coop.util.ApiUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class ExControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseBody
    public ApiResult<?> illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("BAD", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> commentExHandler(CommentNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("COMMENT_NOT_FOUND", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> commentExHandler(MisMatchedPasswordException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("MISMATCHED_PASSWORD", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> purchaseListExHandler(NoPointException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("NO_POINT", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> memberSessionExHandler(NoMemberSessionException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("NO_SESSION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> productExHandler(DuplicatePurchaseProductException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("PURCHASE_DUPLICATE", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> authorizationExHandler(NoAuthorizationException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("NO_AUTHORIZATION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> authenticationExHandler(NoAuthenticationException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("NO_AUTHENTICATION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> validationExHandler(MethodArgumentNotValidException e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("FIELD_INVALID", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    @ResponseBody
    public ApiResult<?> exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return ApiUtil.error("SERVER_ERROR", "내부 오류", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
