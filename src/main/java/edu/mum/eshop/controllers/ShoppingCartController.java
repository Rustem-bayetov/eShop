package edu.mum.eshop.controllers;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.services.AddressService;
import edu.mum.eshop.services.PaymentService;
import edu.mum.eshop.services.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController extends BaseController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @Autowired
    PaymentService paymentService;

    @Autowired
    AddressService addressService;

    @GetMapping("/")
    public String index(Model model) {

        return "/shoppingCart/index";
    }

    @PostMapping("/getCart")
    public @ResponseBody
    ShoppingCart getMyShoppingCart() {

        return shoppingCartService.getMyShoppingCart();
    }

    @PutMapping("/")
    public @ResponseBody
    ZenResult addToCart(@RequestParam Integer productId, @RequestParam Integer quantity) {

        return shoppingCartService.addToCart(productId, quantity);
    }

    @DeleteMapping("/")
    public @ResponseBody
    ShoppingCart deleteFromCart(@RequestParam Integer productId) {

        return shoppingCartService.deleteFromCart(productId);
    }

    @GetMapping("/checkout")
    public String checkout(Model model) {
        model.addAttribute("user", getUser());
        model.addAttribute("shippingAddress", addressService.findShippingAddressByUserId(getUserId()));
        model.addAttribute("billingAddress", addressService.findBillingAddressByUserId(getUserId()));
        model.addAttribute("paymentMethod", paymentService.findSPaymentByUserId(getUserId()));
        model.addAttribute("cart", shoppingCartService.getMyShoppingCart());

        return "/shoppingCart/checkout";
    }

    @PostMapping("/doCheckout")
    public @ResponseBody
    ZenResult doCheckout() {
        return shoppingCartService.checkout(
                addressService.findShippingAddressByUserId(getUserId()),
                addressService.findBillingAddressByUserId(getUserId()),
                paymentService.findSPaymentByUserId(getUserId())
        );
    }

    @GetMapping("/checkoutsuccess")
    public String checkoutSuccess() {

        return "/shoppingCart/checkoutsuccess";
    }

}
