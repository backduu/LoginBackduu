package com.example.helloauthlogin.DTO;

import com.example.helloauthlogin.type.ErrorCode;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginBackduuErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;
}