package edu.mum.eshop.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index(){
        return "redirect:/products/";
    }

    @GetMapping("/access-denied")
    public String accessDenied(){
        return "accessDenied/access-denied";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact/contact";
    }

    @GetMapping("/about")
    public String about(){
        return "about/about";
    }
}
