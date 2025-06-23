package com.example.helloauthlogin.exception;

import com.example.helloauthlogin.type.ErrorCode;
import lombok.Getter;

@Getter
public class LoginBackduuException extends RuntimeException{
    private String message;
    private ErrorCode errorCode;

    public LoginBackduuException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    public LoginBackduuException(ErrorCode errorCode, String detailMessage) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }
}