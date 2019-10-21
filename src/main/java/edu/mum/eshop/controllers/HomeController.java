package edu.mum.eshop.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String login() {
        return "users/login";
    }

    @PreAuthorize("hasAnyAuthority('BUYER', 'SELLER')")
    @GetMapping("/home")
    public String index() {
        return "home/index";
    }
}
