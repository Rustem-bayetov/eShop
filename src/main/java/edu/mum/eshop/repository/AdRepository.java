package edu.mum.eshop.repository;
import edu.mum.eshop.domain.Ad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdRepository  extends JpaRepository<Ad, Integer> {
}
