package com.example.helloauthlogin.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;


/*
Authentication은 Spring Security에서 현재 로그인한 사용자에 대한 정보를 담고 있는 핵심 인터페이스.
login(..., Authentication auth) {
...
} 로 사용해도 좋지만 아래와 같이 구현체들을 각 상황에 맞게 써도 좋다.
- 폼 로그인 기반이면 UsernamePasswordAuthenticationToken이 기본.
- JWT, OAuth2, SSO 같은 외부 인증이면 커스텀 Authentication 구현체
- 테스트 코드에서는 TestingAuthenticationToken

 */
@Controller
public class HomeController {
    @GetMapping("/login")
    public String login() {

        // 단순 화면 호출
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(
            @RequestParam String userId,
            @RequestParam String password,
            HttpServletRequest request
    ) {
        // 이미 로그인된 사용자인지 체크

        // 로그인 실패 메시지 처리

        return "redirect:/login?error=true";
    }
}
