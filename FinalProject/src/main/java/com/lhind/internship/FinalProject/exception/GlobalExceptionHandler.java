package com.lhind.internship.FinalProject.exception;


import com.lhind.internship.FinalProject.model.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<BaseResponse> handleCustomException(CustomException ex) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(baseResponse);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BaseResponse> handleRuntimeException(RuntimeException ex) {
        LOGGER.error("Runtime exception occurred", ex);
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage("Something went wrong");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(baseResponse);
    }
    @ExceptionHandler(DuplicateException.class)
    public ResponseEntity<BaseResponse> handleFlightCreationException(DuplicateException ex) {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(baseResponse);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<BaseResponse> handleAuthenticationException(AuthenticationException ex) {
        LOGGER.info("Invalid login information");
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setMessage(("Invalid username or password"));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(baseResponse);
    }

}