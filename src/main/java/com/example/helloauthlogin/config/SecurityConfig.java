package com.example.helloauthlogin.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.httpBasic(AbstractHttpConfigurer::disable) // basic authentication filter 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/login", "/logout", "/error").permitAll()
                        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 정적 자원에 대해서 인증을 하지 않도록 허가
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/members")
                        .failureForwardUrl("/login")
                        .usernameParameter("userId")
                        .passwordParameter("passWd")
                        .permitAll()
                );
/*
    TODO 기본 오류페이지를 server.error.path에서 지정하여 커스터마이징 해보자.
 */
        return http.build();
    }
}
