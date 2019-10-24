package edu.mum.eshop.controllers;

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
        model.addAttribute("orders", ordersService.getMyCheckoutById(checkout_id).getItems());
        model.addAttribute("total", ordersService.getMyCheckoutById(checkout_id).getTotalSum());
        return "pdf/generatePdf";
    }
}
