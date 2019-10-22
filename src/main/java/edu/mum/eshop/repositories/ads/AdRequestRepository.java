package edu.mum.eshop.repositories.ads;

import edu.mum.eshop.domain.ads.AdRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdRequestRepository extends CrudRepository<AdRequest, Integer> {
    @Query(value = "SELECT * FROM ad_request WHERE ad_request_status = 0", nativeQuery = true)
    List<AdRequest> findUnApprovedAdRequests();
//    public List<AdRequest> findByAdRequest_AdRequestStatus_Created();
}
