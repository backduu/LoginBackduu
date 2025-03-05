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
        /* https://velog.io/@mrcocoball2/Spring-Security-6.1-%EC%9D%B4%ED%9B%84%EC%9D%98-SecurityFilterChain-%EC%84%A4%EC%A0%95 참고 */
        http.httpBasic(AbstractHttpConfigurer::disable) // basic authentication filter 비활성화
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/")).permitAll()
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/login")).permitAll()
                       // .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // 정적 자원에 대해서 인증을 하지 않도록 허가
                        .anyRequest().authenticated())
                .formLogin(form -> form.loginPage("/login").defaultSuccessUrl("/").permitAll())
                .logout(form -> form.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/"));
        return http.build();
    }
}
