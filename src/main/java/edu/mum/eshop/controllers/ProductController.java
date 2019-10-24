package edu.mum.eshop.controllers;

import edu.mum.eshop.EshopApplication;
import edu.mum.eshop.Session;
import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.product.Category;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.product.ProductFilter;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.AdService;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/products")
@SessionAttributes("loggedInUser")
public class ProductController extends BaseController {
    @Autowired ProductService productService;
    @Autowired AdService adService;
    @Autowired UsersService usersService ;
    @ModelAttribute("categories")
    public List<Category> categoriesList() {
        return productService.getCategoris();
    }

    @GetMapping("/")
    public String products(@ModelAttribute("filter") ProductFilter filter, Model model) {
        // System.out.println(filter);

//        System.out.println(getUser());
        List<Ad> ads = adService.get3Ads();
//        System.out.println("\n"+ads);
        model.addAttribute("ads", ads);
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
//        System.out.println("is follwoing "+ isFollowing(getUser().getId(), product.getUser().getId()));
        model.addAttribute("isFollowing", isFollowing(getUser().getId(), product.getUser().getId()));
        return "products/details";
    }
    @GetMapping("/mystore")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public String myStore(@ModelAttribute("filter") ProductFilter filter, Model model) {
        // System.out.println(filter);
//        System.out.println(filter);
        model.addAttribute("products", productService.getMyProducts(filter));
        return "products/mystore";
    }

    @GetMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public String editProduct(@PathVariable int id, Model model) {
//        System.out.println(id);

        Product product = new Product();

        if (id > 0) {
            product = productService.getById(id);
        }

        model.addAttribute("product", product);

        return "products/edit";
    }

    @PostMapping("/edit/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult) {
//        System.out.println(product);

        if (bindingResult.hasErrors()) {
            return "products/edit";
        }

        product = productService.save(product);

        return "redirect:/products/edit/" + product.getId();
    }

    @PostMapping("/promote/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER')")
    public @ResponseBody ZenResult promoteProduct(@PathVariable int id) {
        if (id == 0) return new ZenResult("Please specify product");

        Product product = productService.getById(id);

        return adService.createAdRequest(product);
    }

    @PostMapping("/isPromoted/{id}")
    public @ResponseBody boolean isProductPromoted(@PathVariable int id) {
        if (id == 0) return false;

        return adService.isAdRequestExists(id);
    }

    public Boolean isFollowing(Integer buyerId, Integer sellerId){
        User buyer = usersService.getUserById(buyerId);
        User seller = usersService.getUserById(sellerId);
        List<User> followedSellers = buyer.getFollowedSellers();
        Boolean isFollowed = followedSellers.contains(seller);
        return isFollowed;
    }

}
