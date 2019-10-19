package edu.mum.eshop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/signin")
    public String sigIn(){
        return "users/signin";
    }

    @GetMapping("/signup")
    public String sigUp(){
        return "users/signup";
    }
}
