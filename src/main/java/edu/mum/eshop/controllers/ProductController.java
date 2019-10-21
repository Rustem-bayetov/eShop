package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.product.ProductFilter;
import edu.mum.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/")
    public String products(@ModelAttribute("filter") ProductFilter filter, Model model){
        System.out.println(filter);

        model.addAttribute("categories", productService.getCategoris());
        model.addAttribute("products", productService.getAll(filter));

        return "products/index";
    }
}
