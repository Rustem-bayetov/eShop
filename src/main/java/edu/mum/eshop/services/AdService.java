package edu.mum.eshop.services;
import edu.mum.eshop.domain.Ad;
import java.util.List;
public interface AdService {
    List<Ad> getAll();
    void save(Ad ad);
    Ad getById(Integer id);
}
