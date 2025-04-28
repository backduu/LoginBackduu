package com.example.helloauthlogin.exception;

import com.example.helloauthlogin.DTO.LoginBackduuErrorResponse;
import com.example.helloauthlogin.type.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class LoginBackduuExceptionHandler {
    @ResponseStatus(value= HttpStatus.CONFLICT)
    @ExceptionHandler(LoginBackduuException.class)
    public LoginBackduuErrorResponse exceptionHandler(
            LoginBackduuException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getErrorCode(), request.getRequestURI(), e.getMessage());

        return new LoginBackduuErrorResponse(e.getErrorCode(), e.getMessage());
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public LoginBackduuErrorResponse handleBadRequest(
        Exception e,
        HttpServletRequest request
    ) {
        log.error("url : {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return LoginBackduuErrorResponse.builder()
                .errorCode(ErrorCode.INVALID_REQUEST)
                .errorMessage(ErrorCode.INVALID_REQUEST.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public LoginBackduuErrorResponse handleException(
            Exception e,
            HttpServletRequest request
    ) {
        log.error("url : {}, message: {}",
                request.getRequestURI(), e.getMessage());

        return new LoginBackduuErrorResponse(
                    ErrorCode.INTERNAL_SERVER_ERROR,
                    ErrorCode.INTERNAL_SERVER_ERROR.getMessage()
                );
    }
}
