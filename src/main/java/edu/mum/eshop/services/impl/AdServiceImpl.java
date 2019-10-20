package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.Ad;
import edu.mum.eshop.repository.AdRepository;
import edu.mum.eshop.services.AdService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdServiceImpl implements AdService {
    @Autowired
    AdService adService;
    @Override public List<Ad> getAll() {
        return adService.getAll(); }

    @Override public void save(Ad ad) {
        adService.save(ad); }

    @Override public Ad getById(Integer id) {
        return adService.getById(id); }
}
