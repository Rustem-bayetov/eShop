package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.repositories.UserRepo;
import edu.mum.eshop.repositories.RoleRepo;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public User saveUuyer(User user) {
        return userRepo.save(user);
    }

    @Override
    public Role getRole(String type) {
        return roleRepo.findByType(type);
    }
}
