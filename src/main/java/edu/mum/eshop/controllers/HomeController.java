package edu.mum.eshop.controllers;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import java.security.Principal;

@Controller
// @SessionAttributes("loggedInUser")
public class HomeController {
    @Autowired UsersService usersService;
    @PreAuthorize("hasAnyAuthority('BUYER', 'SELLER', 'ADMIN')")
    @GetMapping("/home")
    public String home(Principal principal, Model model) {
        // model.addAttribute("loggedInUser", usersService.getUserByEmail(principal.getName()));
        return "home/index"; }
    @PreAuthorize("hasAuthority('BUYER')") @GetMapping("/buyer")
    public String buyer() {
        return "home/index";
    }
    @PreAuthorize("hasAuthority('SELLER')") @GetMapping("/seller")
    public String seller() { return "home/index"; }
}
