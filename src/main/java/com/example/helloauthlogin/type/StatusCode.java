package com.example.helloauthlogin.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StatusCode {
    JOIN_MEMBER("가입회원"),
    WITHDRAWN_MEMBER("탈퇴회원");

    private final String enumStatusCode;
}
