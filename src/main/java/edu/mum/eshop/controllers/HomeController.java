package edu.mum.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "login/login";
    }

    @GetMapping("/home")
    public String index(){
        return "home/index";
    }
}
