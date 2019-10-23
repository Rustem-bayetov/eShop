package edu.mum.eshop.services.impl;

import edu.mum.eshop.Session;
import edu.mum.eshop.domain.users.User;
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

}
