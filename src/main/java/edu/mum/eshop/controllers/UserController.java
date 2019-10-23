package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;


@Controller
public class UserController {
    @Autowired
    UsersService usersService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @GetMapping("/registration")
    public String registration(@ModelAttribute("seller") User seller, @ModelAttribute("buyer") User buyer, Model model) {
        model.addAttribute("userType", "buyer");
        return "users/registration";
    }

    @PostMapping("/buyerRegistration")
    public String createNewBuyer(@Valid @ModelAttribute("buyer") User buyer, BindingResult br, Model model) {
        model.addAttribute("seller", new User());
        model.addAttribute("userType", "buyer");

        User userExists = usersService.getUserByEmail(buyer.getEmail());
        if (userExists != null) {
            br.rejectValue("email", "error.user", "Email already exists!");
        }

        if (br.hasErrors()){
            return "users/registration";
        } else {
            buyer.setPassword(bCryptPasswordEncoder.encode(buyer.getPassword()));
            buyer.setActive(true);
            buyer.setRole(usersService.getRole("BUYER"));
            usersService.saveUser(buyer);
            return "redirect:/login";
        }
    }

    @PostMapping("/sellerRegistration")
    public String createNewSeller(@Valid @ModelAttribute("seller") User seller, BindingResult br, Model model) {
        model.addAttribute("buyer", new User());
        model.addAttribute("userType", "seller");

        User userExists = usersService.getUserByEmail(seller.getEmail());
        if (userExists != null) {
            br.rejectValue("email", "error.user", "Email already exists!");
        }
        if (br.hasErrors()){
            return "users/registration";
        } else {
            seller.setPassword(bCryptPasswordEncoder.encode(seller.getPassword()));
            seller.setActive(false);
            seller.setRole(usersService.getRole("SELLER"));
            usersService.saveUser(seller);
            return "redirect:/login";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "users/login";
    }

}
