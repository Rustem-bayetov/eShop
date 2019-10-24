package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.order.OrderCheckout;
import edu.mum.eshop.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PDFController extends BaseController {
    @Autowired
    OrdersService ordersService;

    @PreAuthorize("hasAuthority('BUYER')")
    @GetMapping("/pdf")
    public String generatePdf(@RequestParam("id") String id, Model model){
        Integer checkout_id = Integer.parseInt(id);

        OrderCheckout checkout = ordersService.getMyCheckoutById(checkout_id);
        if (checkout != null) {
            model.addAttribute("orders", checkout.getItems());
            model.addAttribute("total", checkout.getTotalSum());
        }

        return "pdf/generatePdf";
    }
}
