package com.example.helloauthlogin.controller;

import com.example.helloauthlogin.DTO.UserRegisterDTO;
import com.example.helloauthlogin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/signup")
public class SignUpController {
    private final UserService userService;

    @GetMapping
    public String signup() {
        return "signup";
    }

    @PostMapping
    public String signup(@ModelAttribute UserRegisterDTO userDto) {
        // 유저 삽입
        userService.saveSignUp(userDto);

        return "redirect:login";
    }
}
