package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@SessionAttributes("loggedInUser")
@Controller
public class UserController extends BaseController {
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
        if (br.hasErrors()) {
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
        if (br.hasErrors()) {
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
    public String login() {
        return "users/login";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("users/pendingapproval")
    public String getPendingApproval(Model model) {
        List<User> users = usersService.getUnApprovedUsers();
        model.addAttribute("users", users);
        return "users/userstoapprovelist";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("/user/{userid}/{decision}")
    public String approveReview(@PathVariable("userid") Integer id, @PathVariable("decision") Decision decision, Model model) {
        User user = usersService.getUserById(id);
        model.addAttribute("user", user);
        if (decision == Decision.APPROVE) {
//            System.out.println("Approved");
            usersService.decideSellerRequest(user, Decision.APPROVE);
        } else if (decision == Decision.REJECT) {
//            System.out.println("Rejected");
            usersService.decideSellerRequest(user, Decision.REJECT);
        }
        return "redirect:/users/pendingapproval";
    }

    @GetMapping("/user/follow/{sellerid}")
    public String followSeller(Model model, @PathVariable("sellerid") Integer sellerid) {
        User buyer = getUser();
        User seller = usersService.getUserById(sellerid);
        User savedBuyer = usersService.followSeller(seller, buyer);
//        model.addAttribute("savedBuyer", savedBuyer);
        return "redirect:/profile/";
    }

    @GetMapping("/user/unfollow/{sellerid}")
    public String unFollowSeller(Model model, @PathVariable("sellerid") Integer sellerid) {
        User seller = usersService.getUserById(sellerid);
        User savedBuyer = usersService.unFollowSeller(seller, getUser());
        return "redirect:/profile/";
    }
}
