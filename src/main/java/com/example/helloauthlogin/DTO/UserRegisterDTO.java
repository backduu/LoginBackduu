package com.example.helloauthlogin.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRegisterDTO {
    private String name;
    private String age;
    private String dept;
    private String position;
}
