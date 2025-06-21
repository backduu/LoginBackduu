package com.example.helloauthlogin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * PasswordEncoder Config
 */
@Configuration
public class PasswordEncoderConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
        // BCryptPasswordEncoder보다 더 좋은 암호화 방식이다.
        // 훨씬 유연하고, 장기적인 유지보수에 유리
        // 특히 여러 해시 방식이 혼합될 수 있는 환경(DB 마이그레이션 등)에서는 거의 필수예요.
        /*
        < BCryptPasswordEncoder >
        - 단일 암호화 방식: 말 그대로 BCrypt 해싱 알고리즘을 사용하는 PasswordEncoder
        - 고정된 방식: 생성할 때 옵션 외에는 유연성이 거의 없음
        - 보안성: 내부적으로 salting, adaptive hashing을 사용해서 매우 강력한 보안 제공
        - 단점:
            - 다른 암호화 방식과 혼합 사용이 어려움
            - 향후 암호화 방식을 변경할 때 마이그레이션 작업이 번거로울 수 있음

         < DelegatingPasswordEncoder >
        - 다중 암호화 방식 지원: {bcrypt}, {noop}, {argon2} 등 다양한 알고리즘을 지원
        - 접두사 기반 위임 구조: 해시 문자열 앞의 {id}를 보고 어떤 알고리즘으로 인코딩할지 판단
        - 자동 마이그레이션 유도 가능: 예전 비밀번호는 {sha256}, 새로 가입한 유저는 {bcrypt} 등 분기 처리 가능
        - Spring Security가 기본으로 사용하는 방식 (Spring Boot 2 이후)
         */
    }
}
