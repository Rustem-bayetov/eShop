package edu.mum.eshop.domain.ads;
import edu.mum.eshop.domain.Product;
import lombok.Data;

import javax.persistence.*;

@Entity @Data
public class AdRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @OneToOne
    private Product product;
    private AdRequestStatus adRequestStatus;
}
