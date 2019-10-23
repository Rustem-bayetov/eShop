package edu.mum.eshop.domain.users;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Data
public class RejectedUser {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @OneToOne @JoinColumn(name = "user_id")
    @NotNull private User user;
    @NotNull private UserStatus userStatus = UserStatus.REJECTED;
}
