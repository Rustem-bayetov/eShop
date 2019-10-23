package edu.mum.eshop.domain.userinfo;

import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.*;

@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    private String type;

    @NotEmpty
    private String cardName;

//    @Min(19)
//    @Max(19)
    @NotEmpty
    @Pattern(regexp="\\d{19}")
    private String cardNumber;

    @Pattern(regexp = "^[0-3][0-9]/[0-3][0-9]$")
    private String expiration;

    @Pattern(regexp="\\d{3}")
    private String cvv;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
