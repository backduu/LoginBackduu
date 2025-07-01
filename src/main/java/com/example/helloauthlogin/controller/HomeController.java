package com.example.helloauthlogin.controller;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {

        // 단순 화면 호출
        return "login";
    }

    @PostMapping("/doLogin")
    public String login(
            @RequestParam String userId,
            @RequestParam String password,
            HttpServletRequest request,
            RedirectAttributes redirectAttributes
    ) {
        UserRegisterDTO user = userService.findByUserId(userId);

        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "아이디가 존재하지 않습니다.");
            return "redirect:/login";
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            redirectAttributes.addFlashAttribute("error", "비밀번호가 일치하지 않습니다.");
            return "redirect:/login";
        }

        // 로그인 성공 후 세션 저장
        request.getSession().setAttribute("loginUser", user);

        return "redirect:/members"; // 로그인 성공 후 이동할 경로

    }
}
