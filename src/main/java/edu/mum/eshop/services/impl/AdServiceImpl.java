package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.ads.Ad;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.repositories.ads.AdRepository;
import edu.mum.eshop.repositories.ads.AdRequestRepository;
import edu.mum.eshop.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class AdServiceImpl implements AdService {
    @Autowired AdRepository adRepository;
    @Autowired AdRequestRepository adRequestRepository;
    @Override public Iterable<Ad> getAllAds() { return adRepository.findAll(); }
    @Override public Ad saveAd(Ad ad) { return adRepository.save(ad); }
    @Override public Optional<Ad> getById(Integer id) { return adRepository.findById(id); }
    @Override public AdRequest saveAdRequest(AdRequest adRequest) { return adRequestRepository.save(adRequest); }
    @Override public Iterable<AdRequest> getAllAdRequests() { return adRequestRepository.findAll(); }
}
