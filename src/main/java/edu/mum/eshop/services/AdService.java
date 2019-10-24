package edu.mum.eshop.services;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.product.Product;

import java.util.List;
import java.util.Optional;
public interface AdService {
    Iterable<Ad> getAllAds();
    Ad saveAd(Ad ad);
    Ad decideAdRequest(AdRequest adRequest, Decision decision);
    Optional<Ad> getAdById(Integer id);
    AdRequest saveAdRequest(AdRequest adRequest);
    Iterable<AdRequest> getAllAdRequests();
    Optional<AdRequest> getAdRequestById(Integer id);
    Ad approveAdRequestReturnAd(AdRequest adRequest);
    AdRequest approveAdRequestReturnAdRequest(AdRequest adRequest);
    List<AdRequest> getAllNoneApprovedAdRequests();
    ZenResult createAdRequest(Product product);
    boolean isAdRequestExists(Integer productId);
    public List<Ad> get3Ads();
}
