package edu.mum.eshop.domain.userinfo;

import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String type;
    private String cardName;
    private String cardNumber;
    private String expiration;
    private String cvv;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
