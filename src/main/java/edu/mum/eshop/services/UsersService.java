package edu.mum.eshop.services;

import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;

import java.util.List;

public interface UsersService {
    User saveUser(User user);

    Role getRole(String type);

    User getUserByEmail(String email);

    User getUserById(Integer id);

    List<User> getUnApprovedUsers();

    User decideSellerRequest(User user, Decision decision);

    User followSeller(User seller, User buyer);

    User unFollowSeller(User seller, User buyer);

    void addLoyaltyPoints(Integer loyaltyPoints);

    void useLoyaltyPoints(Integer loyaltyPoints);
    List<User> getSellerFollowers(Integer sellerId);
}
