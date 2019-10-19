package edu.mum.eshop.repository;
import edu.mum.eshop.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer> {
}
