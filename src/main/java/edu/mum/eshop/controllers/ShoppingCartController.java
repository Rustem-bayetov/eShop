package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.shoppingCart.AddToCartModel;
import edu.mum.eshop.domain.shoppingCart.ShoppingCart;
import edu.mum.eshop.services.ShoppingCartService;
import org.hibernate.query.QueryParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/shoppingcart")
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    @GetMapping("/")
    public String index(Model model) {
        return "/shoppingCart/index";
    }

    @PostMapping("/getCart")
    public @ResponseBody ShoppingCart getMyShoppingCart(){
        return    shoppingCartService.getMyShoppingCart();
    }

    @PutMapping("/")
    public @ResponseBody ShoppingCart addToCart(@RequestParam Integer productId, @RequestParam Integer quantity) {
        return shoppingCartService.addToCart(productId, quantity);
    }

    @DeleteMapping("/")
    public @ResponseBody ShoppingCart deleteFromCart(@RequestParam Integer productId) {
        return shoppingCartService.deleteFromCart(productId);
    }

    @GetMapping("/checkout")
    public String checkout(){
        return "/shoppingCart/checkout";
    }
}
