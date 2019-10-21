package edu.mum.eshop.domain.users;

import edu.mum.eshop.domain.PurchaseOrder;
import edu.mum.eshop.domain.product.Product;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Entity
@Table(name = "user")
public class User {

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id")
    private Role role;

    @OneToMany
    private List<Product> products;
    @OneToMany
    private List<PurchaseOrder> purchaseOrders;

}

