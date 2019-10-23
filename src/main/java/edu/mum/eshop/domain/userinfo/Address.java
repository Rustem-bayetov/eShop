package edu.mum.eshop.domain.userinfo;

import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;

@Data
@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String street;

    @NotEmpty
    private String city;

    @NotEmpty
    private String state;

    @NotEmpty
    private String zipcode;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private boolean shipping;
    private boolean billing;
}
