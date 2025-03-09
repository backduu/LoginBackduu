package com.example.helloauthlogin.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeveloperJobType {
    WINDOWS("윈도우프로그래밍"),
    EMBEDDED("임베디드"),
    WEB_BACKEND("백엔드"),
    WEB_FRONTEND("프론트엔드"),
    WEB_FULLSTACK("풀스택");

    private final String enumDeveloperJobType;
}
