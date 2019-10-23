package edu.mum.eshop.controllers;

import edu.mum.eshop.Session;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.domain.users.UserType;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseController {
    @Autowired
    Session session;

    User getUser() {
        return session.getUser();
    }

    Integer getUserId(){
        return session.getUserId();
    }

    void clearSessionUsers(){
        session.clearUsers();
    }

    boolean isInRole(UserType role) {
        return session.isInRole(role);
    }
}
