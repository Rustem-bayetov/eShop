package edu.mum.eshop.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("loggedInUser")
@PreAuthorize("hasAnyAuthority('BUYER', 'SELLER', 'ADMIN')")
public class ProfileController {
    @GetMapping("/profile")
    public String profile() {
        return "users/profile";
    }
}
