package edu.mum.eshop.domain.ads;

import edu.mum.eshop.domain.product.Product;
import lombok.Data;

import javax.persistence.*;
@Entity @Data
public class AdRequest {
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Id
    private Integer id;
    @OneToOne
    private Product product;

    public AdRequest() {
    }

    public AdRequest(Product product, AdRequestStatus adRequestStatus) {
        this.product = product;
        this.adRequestStatus = adRequestStatus;
    }
    private AdRequestStatus adRequestStatus;
}
