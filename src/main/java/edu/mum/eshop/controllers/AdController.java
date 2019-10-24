package edu.mum.eshop.controllers;

import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.AdRequestStatus;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.services.AdService;
import edu.mum.eshop.services.ProductService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("get")
    public String get(Model model) {
        /*get all unapproved ad requests (not ads )*/
//        List<Ad> ads = Util.iterableToCollection(adService.getAllAds());
        List<AdRequest> ads = adService.getAllNoneApprovedAdRequests();
        model.addAttribute("ads", ads);
        return "ad/adList";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("{adid}/{decision}")
    public String approveAd(@PathVariable("adid") Integer id, @PathVariable("decision") Decision decision, Model model) {
        Optional<AdRequest> ad = adService.getAdRequestById(id);
        Product prod = productService.getById(ad.get().getProduct().getId());
        model.addAttribute("ad", ad.get());
        model.addAttribute("prod", prod);
        if (decision == Decision.APPROVE) {
//            System.out.println("Approved");
            adService.decideAdRequest(ad.get(), Decision.APPROVE);
        } else if (decision == Decision.REJECT) {
//            System.out.println("Rejected");
            adService.decideAdRequest(ad.get(), Decision.REJECT);
        }
        return "redirect:/ad/adList";
    }

}