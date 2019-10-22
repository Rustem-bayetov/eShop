package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.repositories.ads.AdRepository;
import edu.mum.eshop.repositories.ads.AdRequestRepository;
import edu.mum.eshop.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdServiceImpl implements AdService {
    @Autowired AdRepository adRepository;
    @Autowired AdRequestRepository adRequestRepository;
    @Override public Iterable<Ad> getAllAds() { return adRepository.findAll(); }
    @Override public Ad saveAd(Ad ad) { return adRepository.save(ad); }

    @Override
    public Optional<Ad> getAdById(Integer id) {
        return adRepository.findById(id);
    }

    @Override
    public Optional<AdRequest> getAdRequestById(Integer id) {
        return adRequestRepository.findById(id);
    }
    @Override public AdRequest saveAdRequest(AdRequest adRequest) { return adRequestRepository.save(adRequest); }
    @Override public Iterable<AdRequest> getAllAdRequests() { return adRequestRepository.findAll(); }

    @Override
    public Ad approveAdRequestReturnAd(AdRequest adRequest) {
        // change status
//        adRequest.setAdRequestStatus(AdRequestStatus.APPROVED);
        // save change to db
        AdRequest savedAdRequest = adRequestRepository.save(adRequest);
        // create a new Ad
        Ad newAdd = new Ad();
        newAdd.setProduct(savedAdRequest.getProduct());
        newAdd.setAdRequest(savedAdRequest);
        // save new Ad
        Ad savedAd = adRepository.save(newAdd);
        return savedAd;
    }

    @Override
    public AdRequest approveAdRequestReturnAdRequest(AdRequest adRequest) {
        Ad newAdd = new Ad();
        newAdd.setProduct(adRequest.getProduct());
        newAdd.setAdRequest(adRequest);
        Ad savedAd = adRepository.save(newAdd);
        return adRequest;
    }


}
