package edu.mum.eshop.domain.users;

import lombok.Data;

import javax.persistence.Entity;

@Entity @Data
public class RejectedUser {
    private Integer id ;
    private User user;
    private UserStatus userStatus = UserStatus.REJECTED;
}
