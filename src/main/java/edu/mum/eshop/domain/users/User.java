package edu.mum.eshop.domain.users;

import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.domain.userinfo.Address;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    public User(){
        this.loyaltyPoints = 0;
    }

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    List<User> followedSellers;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Email
    @NotEmpty
    @Column(name = "email")
    private String email;

    @Size(min = 5, message = "{Size.password}")
    @NotEmpty
    @Column(name = "password")
    private String password;

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "loyalty_points")
    private Integer loyaltyPoints;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Product> products;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<Address> addresses;

//    @OneToMany
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private List<PurchaseOrder> purchaseOrders;
//     @OneToMany
//     private List<PurchaseOrder> purchaseOrders;
//    @OneToOne(cascade = CascadeType.ALL)
//    private Payment payment;
}