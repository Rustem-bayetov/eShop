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
        return "/ad/viewaddetails";
    }
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @GetMapping("createAds")
    public String createAds(){
        Product p1 = productService.getById(1);
        Product p2 = productService.getById(1);
        Product p3 = productService.getById(2);
        AdRequest ar1 = new AdRequest();
        AdRequest ar2 = new AdRequest();
        AdRequest ar3 = new AdRequest();
        ar1.setProduct(p1);
        ar2.setProduct(p2);
        ar3.setProduct(p3);
        Ad ad1 = new Ad();
        Ad ad2 = new Ad();
        Ad ad3 = new Ad();
        ar1.setAdRequestStatus(AdRequestStatus.CREATED);
        ar2.setAdRequestStatus(AdRequestStatus.CREATED);
        ar3.setAdRequestStatus(AdRequestStatus.CREATED);
        ad1.setAdRequest(ar1);
        ad2.setAdRequest(ar2);
        ad3.setAdRequest(ar3);
        ad1.setProduct(p1);
        ad2.setProduct(p2);
        ad3.setProduct(p3);
        adService.saveAdRequest(ar1);
        adService.saveAdRequest(ar2);
        adService.saveAdRequest(ar3);
        adService.saveAd(ad1);
        adService.saveAd(ad2);
        adService.saveAd(ad3);
        return "redirect:/ad/get";
    }
}