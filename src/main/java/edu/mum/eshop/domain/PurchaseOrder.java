package edu.mum.eshop.domain;

import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    private Product product;

    @ManyToOne
    private User user;
}
