package com.example.helloauthlogin.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AuthorityDTO {
    private Long userId;
    private Long authority;
}