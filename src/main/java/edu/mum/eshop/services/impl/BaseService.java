package edu.mum.eshop.services.impl;

import edu.mum.eshop.Session;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.domain.users.UserType;
import org.springframework.beans.factory.annotation.Autowired;

public class BaseService {
    @Autowired
    Session session;

    User getUser() {
        return session.getUser();
    }

    Integer getUserId() {
        return session.getUserId();
    }

    void clearSessionUsers() {
        session.clearUsers();
    }

    boolean isUserAuthorized(){
        return session.isUserAuthorized();
    }

    boolean isInRole(UserType role){
        return session.isInRole(role);
    }
}
