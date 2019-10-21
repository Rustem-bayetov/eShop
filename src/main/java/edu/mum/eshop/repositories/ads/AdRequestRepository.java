package edu.mum.eshop.repositories.ads;
import edu.mum.eshop.domain.ads.AdRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
@Repository public interface AdRequestRepository extends CrudRepository<AdRequest, Integer> { }
