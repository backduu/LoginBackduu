package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/main.do")
    public ModelAndView mainPage() {
        ModelAndView mv = new ModelAndView();

        //TODO 20241109
        /*
            추후 로그인 시 로그인 정보를 mv에 설정하여 jsp에 던질 예정
         */
        mv.setViewName("mainPage");
        return mv;
    }
}
