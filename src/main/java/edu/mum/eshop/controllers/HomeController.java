package edu.mum.eshop.controllers;

import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {
//    @Autowired
//    UsersService usersService;

    @GetMapping("/")
    public String index(){
        return "home/index";
    }

//    @PreAuthorize("hasAnyAuthority('BUYER', 'SELLER', 'ADMIN')")
//    @GetMapping("/home")
//    public String home(Principal principal, Model model) {
//        return "home/index";
//    }
//
//    @PreAuthorize("hasAuthority('BUYER')")
//    @GetMapping("/buyer")
//    public String buyer() {
//        return "home/index";
//    }
//
//    @PreAuthorize("hasAuthority('SELLER')")
//    @GetMapping("/seller")
//    public String seller() {
//        return "home/index";
//    }
}
