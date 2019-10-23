package edu.mum.eshop;


import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.domain.users.UserType;
import edu.mum.eshop.repositories.UserRepo;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.services.impl.UsersServiceImpl;
import edu.mum.eshop.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@Component
public class Session {
    private static HashMap<String, User> users;

    @Autowired
    UserRepo userRepo;

    public Session(){
        users = new HashMap<>();
    }

    public void clearUsers(){
        users = new HashMap<>();
    }

    public boolean isInRole(UserType role) {
        Collection<SimpleGrantedAuthority> authorities = (Collection<SimpleGrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        for (SimpleGrantedAuthority authority : authorities) {
            if (authority.toString().equals(role.name())) return true;
        }

        return false;
    }

    public boolean isUserAuthorized(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) return false;
        if (auth.getName() == "anonymousUser") return false;

        return true;
    }

    public Integer getUserId() {
        User user = getUser();
        return user != null ? user.getId() : 0;
    }

    public User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.isAuthenticated()) return null;
        if (auth.getName() == "anonymousUser") return null;

        if (!users.containsKey(auth.getName())) {
            // if we don't know about user
            users.put(auth.getName(), userRepo.findUserByEmail(auth.getName()));
        }

        return users.get(auth.getName());
    }
}
