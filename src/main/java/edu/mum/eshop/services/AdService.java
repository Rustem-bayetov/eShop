package edu.mum.eshop.services;

import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;

import java.util.Optional;
public interface AdService {
    Iterable<Ad> getAllAds();
    Ad saveAd(Ad ad);

    Optional<Ad> getAdById(Integer id);
    AdRequest saveAdRequest(AdRequest adRequest);
    Iterable<AdRequest> getAllAdRequests();

    Optional<AdRequest> getAdRequestById(Integer id);

    Ad approveAdRequestReturnAd(AdRequest adRequest);

    AdRequest approveAdRequestReturnAdRequest(AdRequest adRequest);
}
