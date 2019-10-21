package edu.mum.eshop.repositories.ads;
import edu.mum.eshop.domain.ads.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AdRepository  extends CrudRepository<Ad, Integer> { }
