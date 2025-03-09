package com.example.helloauthlogin.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeveloperLevel {
    INTERN_DEV("인턴 개발자"),
    JUNIOR_DEV("초급 개발자"),
    INTERMEDIATE_DEV("중급 개발자"),
    SENIOR_DEV("고급 개발자"),
    SPECIAL_DEV("특급 개발자");

    private final String enumDeveloperLevel;
}
