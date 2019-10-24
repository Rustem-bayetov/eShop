package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController {

    @Autowired
    OrdersService ordersService;

    @GetMapping("/")
    public String orders(Model model) {
        List<OrderCheckout> checkouts = ordersService.getMyCheckouts();

        System.out.println(checkouts);

        model.addAttribute("checkouts", checkouts);
        return "orders/index";
    }
}
