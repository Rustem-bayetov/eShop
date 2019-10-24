package edu.mum.eshop.controllers;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.domain.order.OrderItem;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/my")
    @PreAuthorize("hasAnyAuthority('BUYER')")
    public String orders(Model model) {
        List<OrderCheckout> checkouts = ordersService.getMyCheckouts();

        System.out.println(checkouts);

        model.addAttribute("checkouts", checkouts);
        return "orders/my";
    }

    @GetMapping("/sales")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public String sales(Model model) {
        List<OrderItem> sales = ordersService.getMySales();

        System.out.println(sales);

        model.addAttribute("sales", sales);
        return "orders/sales";
    }

    @PostMapping("/shipOrder/{orderId}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public @ResponseBody ZenResult shipOrder(@PathVariable int orderId) {
        if (orderId == 0) return new ZenResult("Please choose order");

        return ordersService.shipOrder(orderId);
    }

    @PostMapping("/deliverOrder/{orderId}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public @ResponseBody ZenResult deliverOrder(@PathVariable int orderId) {
        if (orderId == 0) return new ZenResult("Please choose order");

        return ordersService.orderDelivered(orderId);
    }

    @PostMapping("/cancelOrder/{orderId}")
    @PreAuthorize("hasAnyAuthority('BUYER')")
    public @ResponseBody ZenResult cancelOrder(@PathVariable int orderId) {
        if (orderId == 0) return new ZenResult("Please choose order");

        return ordersService.cancelOrder(orderId);
    }

    @PostMapping("/returnItem/{orderId}")
    @PreAuthorize("hasAnyAuthority('BUYER')")
    public @ResponseBody ZenResult returnItem(@PathVariable int orderId) {
        if (orderId == 0) return new ZenResult("Please choose order");

        return ordersService.returnOrder(orderId);
    }
}
