package edu.mum.eshop.services.impl;

import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.users.RejectedUser;
import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.domain.users.UserStatus;
import edu.mum.eshop.repositories.RoleRepo;
import edu.mum.eshop.repositories.UserRepo;
import edu.mum.eshop.services.RejectedUserService;
import edu.mum.eshop.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsersServiceImpl extends BaseService implements UsersService {
    @Autowired
    RoleRepo roleRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    RejectedUserService rejectedUserService;

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
    public List<User> getUnApprovedUsers() {
        return userRepo.getPendingApprovalUsers();
    }

    @Override
    public User decideSellerRequest(User user, Decision decision) {
        if (decision == Decision.APPROVE) {
            user.setActive(true);
            userRepo.save(user);
        } else if (decision == Decision.REJECT) {
            RejectedUser rejectedUser = new RejectedUser();
            rejectedUser.setUser(user);
            rejectedUser.setUserStatus(UserStatus.REJECTED);
            rejectedUserService.save(rejectedUser);
        }
        User savedUser = userRepo.save(user);
        return savedUser;
    }

    @Override
    public User followSeller(User seller, User buyer) {
        List<User> sellers = new ArrayList<>();
        if (seller.getRole().getId() == 2) {
            sellers.add(seller);
            List<User> oldList = buyer.getFollowedSellers();
            if (oldList.size() > 0) for (int i = 0; i < oldList.size(); i++) sellers.add(oldList.get(i));
            buyer.setFollowedSellers(sellers);
            return userRepo.save(buyer);
        } else {
            return buyer;
        }
    }

    @Override
    public User unFollowSeller(User seller, User buyer) {
//            List<User> oldList = buyer.getFollowedSellers();
//            oldList.remove(seller);
//            System.out.println(oldList);
//            buyer.setFollowedSellers(oldList);
        userRepo.unfollowSellerDB(buyer.getId(), seller.getId());
        System.out.println("followed sellers count " + buyer.getFollowedSellers().size());
        return buyer;
    }

    @Override
    public void addLoyaltyPoints(Integer userId, Integer loyaltyPoints) {
        User user = getUserById(userId);
        user.setLoyaltyPoints(user.getLoyaltyPoints() + loyaltyPoints);

        userRepo.save(user);
        clearSessionUsers();
    }

    @Override
    public void useLoyaltyPoints(Integer loyaltyPoints) {
        User user = getUserById(getUserId());
        user.setLoyaltyPoints(user.getLoyaltyPoints() - loyaltyPoints);

        userRepo.save(user);
        clearSessionUsers();
    }
}