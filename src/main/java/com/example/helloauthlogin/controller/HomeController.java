package com.example.helloauthlogin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

@Controller
public class HomeController {
    @GetMapping("/login")
    public String login() {

        return "login";
    }
}
