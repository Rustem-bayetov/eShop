package edu.mum.eshop.services.impl;

import edu.mum.eshop.classes.ZenResult;
import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.AdRequestStatus;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.repositories.ads.AdRepository;
import edu.mum.eshop.repositories.ads.AdRequestRepository;
import edu.mum.eshop.services.AdService;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class AdServiceImpl implements AdService {
    @Autowired
    AdRepository adRepository;
    @Autowired
    AdRequestRepository adRequestRepository;
    @Override
    public Iterable<Ad> getAllAds() {
        return adRepository.findAll();
    }

    @Override
    public Ad saveAd(Ad ad) {
        return adRepository.save(ad);
    }

    @Override
    public Optional<Ad> getAdById(Integer id) {
        return adRepository.findById(id);
    }

    @Override
    public Optional<AdRequest> getAdRequestById(Integer id) {
        return adRequestRepository.findById(id);
    }

    @Override
    public AdRequest saveAdRequest(AdRequest adRequest) {
        return adRequestRepository.save(adRequest);
    }

    @Override
    public Iterable<AdRequest> getAllAdRequests() {
        return adRequestRepository.findAll();
    }

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

    @Override
    public List<AdRequest> getAllNoneApprovedAdRequests() {
        return adRequestRepository.findUnApprovedAdRequests();
    }

    @Override
    public Ad decideAdRequest(AdRequest adRequest, Decision decision) {
        if (decision == Decision.APPROVE) {
            adRequest.setAdRequestStatus(AdRequestStatus.APPROVED);
            AdRequest savedAdRequest = adRequestRepository.save(adRequest);
            Ad ad = new Ad();
            ad.setProduct(adRequest.getProduct());
            ad.setAdRequest(adRequest);
            // save Ad
            Ad savedAd = adRepository.save(ad);
            return savedAd;
        } else if (decision == Decision.REJECT) {
            adRequest.setAdRequestStatus(AdRequestStatus.REJECTED);
            AdRequest savedAdRequest = adRequestRepository.save(adRequest);
            return null;
        }
        return null;
    }
    @Override public ZenResult createAdRequest(Product product) {
        if (isAdRequestExists(product.getId())) {
            return new ZenResult("This product is already promoted");
        }
        AdRequest request = new AdRequest();
        request.setProduct(product);
        request.setAdRequestStatus(AdRequestStatus.CREATED);
        request = adRequestRepository.save(request);
        ZenResult result = new ZenResult();
        request.setId(request.getId());
        return result;
    }
    @Override public boolean isAdRequestExists(Integer productId) {
        List<AdRequest> existingRequest = adRequestRepository.findAdRequestByProductId(productId);
        return (existingRequest != null && existingRequest.size() > 0);
    }
    @Override public List<Ad> get3Ads(){
        List<Ad> allAds = Util.iterableToCollection(adRepository.findAll());
        for (int i = 0 ; i < allAds.size() ; i++){
            if (allAds.get(i).getAdRequest().getAdRequestStatus() == AdRequestStatus.REJECTED) {
                allAds.remove(i);
                i--;
            }
        }
        if (allAds.size() <= 3) return allAds;
        else {
            List<Ad> random3Ads = new ArrayList<>();
            int size = allAds.size();
            Random random = new Random();
            int random2 , random3;
            int random1 = random.ints(0,(size)).findFirst().getAsInt();
            random2 = random.ints(0, (size)).findFirst().getAsInt();
            random3 = random.ints(0, (size)).findFirst().getAsInt();
            while (random2 == random1) {
                random2 = random.ints(0, (size)).findFirst().getAsInt(); }
            while (random3 == random1 && random3 == random2) {
                random3 = random.ints(0, (size)).findFirst().getAsInt(); }

            random3Ads.add(allAds.get(random1));
            random3Ads.add(allAds.get(random2));
            random3Ads.add(allAds.get(random3));
            return random3Ads;
        }
    }
}
