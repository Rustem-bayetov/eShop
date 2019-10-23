package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.userinfo.Address;
import edu.mum.eshop.domain.userinfo.Payment;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.AddressService;
import edu.mum.eshop.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@SessionAttributes("loggedInUser")
@PreAuthorize("hasAnyAuthority('BUYER', 'SELLER', 'ADMIN')")
public class ProfileController {
    @Autowired
    AddressService addressService;
    @Autowired
    PaymentService paymentService;

    private User user;

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        user = (User) session.getAttribute("loggedInUser");
        model.addAttribute("payment", new Payment());
        Address shippingAddress = addressService.findShippingAddressByUserId(user.getId());
        Address billingAddress = addressService.findBillingAddressByUserId(user.getId());

        if (shippingAddress == null) {
            model.addAttribute("shippingAddress", new Address());
        } else {
            model.addAttribute("shippingAddress", shippingAddress);
        }

        if (billingAddress == null) {
            model.addAttribute("billingAddress", new Address());
        } else {
            model.addAttribute("billingAddress", billingAddress);
        }

        return "users/profile";
    }

    @PostMapping("/saveBilling")
    public String saveBilling(@Valid Address address, BindingResult br, Model model, HttpSession session) {
        user = (User) session.getAttribute("loggedInUser");
        address.setUser(user);
        address.setBilling(true);
        model.addAttribute("active", "true");

        if (br.hasErrors()) {
            return "users/profile";
        }
        addressService.saveAddress(address);
        return "redirect:/profile";
    }

    @PostMapping("/saveShipping")
    public String saveShipping(@Valid Address address, BindingResult br, Model model, HttpSession session) {
        user = (User) session.getAttribute("loggedInUser");
        address.setUser(user);
        address.setShipping(true);
        model.addAttribute("active", "true");

        if (br.hasErrors()) {
            return "users/profile";
        }

        addressService.saveAddress(address);
        return "redirect:/profile";
    }

    @PostMapping("/savePayment")
    public String savePayment(Payment payment, Model model, HttpSession session) {
        user = (User) session.getAttribute("loggedInUser");
        payment.setUser(user);
        model.addAttribute("payActive", "true");

//        if (br.hasErrors()) {
//            return "users/profile";
//        }

        paymentService.savePaymentInfo(payment);
        return "redirect:/profile";
    }
}
