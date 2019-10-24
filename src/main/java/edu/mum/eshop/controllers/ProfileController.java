package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.userinfo.Address;
import edu.mum.eshop.domain.userinfo.Payment;
import edu.mum.eshop.services.AddressService;
import edu.mum.eshop.services.PaymentService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@PreAuthorize("hasAnyAuthority('BUYER', 'SELLER', 'ADMIN')")
@RequestMapping("/profile")
public class ProfileController extends BaseController {
    @Autowired
    AddressService addressService;
    @Autowired
    PaymentService paymentService;
    @Autowired
    UsersService usersService;

    @GetMapping("/")
    public String profile(Model model) {
        setBilling(model);
        setShipping(model);
        setPayment(model);
        model.addAttribute("active", "products");
        return "users/profile";
    }

    @PostMapping("/billing")
    public String saveBilling(@Valid @ModelAttribute("billingAddress") Address address, BindingResult br, Model model) {
        address.setUser(getUser());
        address.setBilling(true);
        model.addAttribute("active", "addresses");

        if (br.hasErrors()) {
            setShipping(model);
            setPayment(model);
            return "users/profile";
        }
        addressService.saveAddress(address);
        return "redirect:/profile/";
    }

    @PostMapping("/shipping")
    public String saveShipping(@Valid @ModelAttribute("shippingAddress") Address address, BindingResult br, Model model) {
        address.setUser(getUser());
        address.setShipping(true);
        model.addAttribute("active", "addresses");

        if (br.hasErrors()) {
            setBilling(model);
            setPayment(model);
            return "users/profile";
        }

        addressService.saveAddress(address);
        return "redirect:/profile/";
    }

    @PostMapping("/payment")
    public String savePayment(@Valid Payment payment, BindingResult br, Model model) {
        payment.setUser(getUser());
        model.addAttribute("active", "payment");

        if (br.hasErrors()) {
            setBilling(model);
            setShipping(model);
            return "users/profile";
        }

        paymentService.savePaymentInfo(payment);
        return "redirect:/profile/";
    }

    @GetMapping("/address/delete")
    public String deleteShipping(@RequestParam("id") String id){
        Integer addr_id = Integer.parseInt(id);
        Address address = addressService.findAddressById(addr_id);
        System.out.println(address);
        if (address != null){
            addressService.deleteAddressById(addr_id);
        }
        return "redirect:/profile/";
    }

    @GetMapping("/payment/delete")
    public String deletePayment(@RequestParam("id") String id){
        Integer pay_id = Integer.parseInt(id);
        Payment payment = paymentService.findPaymentById(pay_id);
        System.out.println(payment);
        if (payment != null) {
            paymentService.deletePaymentById(pay_id);
        }
        return "redirect:/profile/";
    }

    private void setBilling(Model model) {
        Address billingAddress = addressService.findBillingAddressByUserId(getUser().getId());
        if (billingAddress == null) {
            model.addAttribute("billingAddress", new Address());
        } else {
            model.addAttribute("billingAddress", billingAddress);
        }
    }

    private void setShipping(Model model) {
        Address shippingAddress = addressService.findShippingAddressByUserId(getUser().getId());

        if (shippingAddress == null) {
            model.addAttribute("shippingAddress", new Address());
        } else {
            model.addAttribute("shippingAddress", shippingAddress);
        }
    }

    private void setPayment(Model model) {
        Payment payment = paymentService.findSPaymentByUserId(getUser().getId());

        if (payment == null) {
            model.addAttribute("payment", new Payment());
        } else {
            model.addAttribute("payment", payment);
        }
    }


}
