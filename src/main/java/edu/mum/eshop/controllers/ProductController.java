package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.product.ProductFilter;
import edu.mum.eshop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
@SessionAttributes("loggedInUser")

public class ProductController {

    @Autowired
    ProductService productService;

    @ModelAttribute("categories")
    public List<Category> categoriesList() {
        return productService.getCategoris();
    }

    @GetMapping("/")
    public String products(@ModelAttribute("filter") ProductFilter filter, Model model) {
        System.out.println(filter);

        model.addAttribute("products", productService.getAllProducts(filter));

        return "products/index";
    }

    @GetMapping("/{id}")
    public String details(@PathVariable int id, Model model) {
        Product product = new Product();

        if (id > 0) {
            product = productService.getById(id);
        }

        model.addAttribute("product", product);

        return "products/details";
    }

    @GetMapping("/mystore")
    public String myStore(@ModelAttribute("filter") ProductFilter filter, Model model) {
        System.out.println(filter);

        model.addAttribute("products", productService.getMyProducts(filter));

        return "products/mystore";
    }

    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable int id, Model model) {
        System.out.println(id);

        Product product = new Product();

        if (id > 0) {
            product = productService.getById(id);
        }

        model.addAttribute("product", product);

        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
        System.out.println(product);

        if (bindingResult.hasErrors()) {
            return "products/edit";
        }

        product = productService.save(product);

        return "redirect:/products/edit/" + product.getId();
    }

}
