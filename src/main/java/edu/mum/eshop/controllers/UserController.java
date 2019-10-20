package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UserController {
    @Autowired
    UsersService buyerService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("user") User user) {
        return "login/registration";
    }

    @PostMapping("/buyerRegistration")
    public String createNewBuyer(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole(buyerService.getRole("BUYER"));
        buyerService.saveUuyer(user);
        return "redirect:/login";
    }

    @PostMapping("/sellerRegistration")
    public String createNewSeller(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setActive(true);
        user.setRole(buyerService.getRole("SELLER"));
        buyerService.saveUuyer(user);
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String success(){
        return "login/login";
    }
}
