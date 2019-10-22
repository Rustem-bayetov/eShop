package edu.mum.eshop.domain.shoppingCart;

import edu.mum.eshop.domain.users.User;
import lombok.Data;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany
    @JoinColumn(name = "shopping_cart_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<ShoppingCartItem> items;

    private double totalSum;



}
