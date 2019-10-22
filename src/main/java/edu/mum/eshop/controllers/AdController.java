package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.AdRequestStatus;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.services.AdService;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@SessionAttributes("loggedInUser")
@RequestMapping("/ad")
@Controller
public class AdController {
    @Autowired
    AdService adService;
    @Autowired
    ProductService productService;
    @Autowired
    UsersService usersService;

    @PreAuthorize("hasAnyAuthority('SELLER', 'ADMIN')")
    @GetMapping("get")
    public String get(Model model) {
        List<Ad> ads = Util.iterableToCollection(adService.getAllAds());
        model.addAttribute("ads", ads);
        return "ad/adList";
    }

    @PreAuthorize("hasAnyAuthority('SELLER', 'ADMIN')")
    @GetMapping("{adid}/{decision}")
    public String approveAd(@PathVariable("adid") Integer id, @PathVariable("decision") Decision decision, Model model) {
        Optional<Ad> ad = adService.getAdById(id);
        Product prod = productService.getById(ad.get().getProduct().getId());
        model.addAttribute("ad", ad.get());
        model.addAttribute("prod", prod);
        if (decision == Decision.APPROVE) System.out.println("Approved");
        else if (decision == Decision.REJECT) System.out.println("Rejected");
        return "/ad/viewaddetails";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("getcreate")
    public String createAd(@ModelAttribute("adRequest") AdRequest adRequest, @ModelAttribute("product") Product product) {
        /* insert product validation code when needed */
        AdRequest adRequest1 = adService.saveAdRequest(new AdRequest(productService.getById(1), AdRequestStatus.CREATED));
        AdRequest adRequest2 = adService.saveAdRequest(new AdRequest(productService.getById(2), AdRequestStatus.APPROVED));
        AdRequest adRequest3 = adService.saveAdRequest(new AdRequest(productService.getById(3), AdRequestStatus.REJECTED));
        AdRequest approvedAdRequest1 = adService.approveAdRequestReturnAdRequest(adRequest1);
        AdRequest approvedAdRequest2 = adService.approveAdRequestReturnAdRequest(adRequest2);
        AdRequest approvedAdRequest3 = adService.approveAdRequestReturnAdRequest(adRequest3);
        return "redirect:/ad/get";
    }
}