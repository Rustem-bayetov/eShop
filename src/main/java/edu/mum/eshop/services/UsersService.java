package edu.mum.eshop.services;

import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.review.Review;
import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;

import java.util.List;

public interface UsersService {
    public User saveUser(User user);
    public Role getRole(String type);
    public User getUserByEmail(String email);
    public User getUserById(Integer id);
    public List<User> getUnApprovedUsers();
    public User decideSellerRequest(User user, Decision decision);
    public User followSeller(User seller, User buyer);
}
