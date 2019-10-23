package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.domain.users.UserStatus;
import edu.mum.eshop.repositories.RejectedUserRepository;
import edu.mum.eshop.repositories.UserRepo;
import edu.mum.eshop.repositories.RoleRepo;
import edu.mum.eshop.services.RejectedUserService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl extends BaseService implements UsersService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;

    @Override
    public User saveUser(User user) {
        User resultUser = userRepo.save(user);
        clearSessionUsers();
        return resultUser;
    }

    @Override
    public Role getRole(String type) {
        return roleRepo.findByType(type);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findUserByEmail(email);
    }

    @Override
    public User getUserById(Integer id) {
        return userRepo.findById(id).get();
    }
    @Override
    public List<User> getUnApprovedUsers(){
        return userRepo.getPendingApprovalUsers();
    }

    @Override
    public User decideSellerRequest(User user, Decision decision) {
        if (decision == Decision.APPROVE) {
            user.setActive(true);
            userRepo.save(user);
        }
        else if (decision == Decision.REJECT) {
            RejectedUser rejectedUser = new RejectedUser();
            rejectedUser.setUser(user);
            rejectedUser.setUserStatus(UserStatus.REJECTED);
            rejectedUserService.save(rejectedUser);
        }
        User savedUser = userRepo.save(user);
        return savedUser;
    }
}
