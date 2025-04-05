package com.example.helloauthlogin.DTO;

import jakarta.validation.constraints.*;
import lombok.*;
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class UserRegisterDTO {

    @NotBlank(message = "이름은 필수 항목입니다.")
    @Size(min = 2, max = 30, message = "이름은 2자 이상, 30자 이하여야 합니다.")
    private String name;
    private String age;
    private String position;
    private String dept;
    @NotBlank(message = "아이디는 필수 항목입니다.")
    @Size(min = 2, max = 30, message = "이름은 2자 이상, 30자 이하여야 합니다.")
    private String id;
    @NotBlank(message = "이름은 필수 항목입니다.")
    @Size(min = 6, max = 30, message = "이름은 2자 이상, 30자 이하여야 합니다.")
    private String password;
}
