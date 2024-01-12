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
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ExControllerAdvice {

    @ExceptionHandler(IllegalArgumentException.class)
//    @ResponseBody
    public ModelAndView illegalExHandler(IllegalArgumentException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("BAD", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView commentExHandler(CommentNotFoundException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("COMMENT_NOT_FOUND", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView commentExHandler(MisMatchedPasswordException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("MISMATCHED_PASSWORD", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView purchaseListExHandler(NoPointException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("NO_POINT", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView memberSessionExHandler(NoMemberSessionException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("NO_SESSION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView productExHandler(DuplicatePurchaseProductException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("PURCHASE_DUPLICATE", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView authorizationExHandler(NoAuthorizationException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("NO_AUTHORIZATION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView authenticationExHandler(NoAuthenticationException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("NO_AUTHENTICATION", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView validationExHandler(MethodArgumentNotValidException e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("FIELD_INVALID", e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
//    @ResponseBody
    public ModelAndView exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ModelAndView("redirect:/error");
//        return ApiUtil.error("SERVER_ERROR", "내부 오류", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
