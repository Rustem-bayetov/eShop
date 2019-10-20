package edu.mum.eshop.services;

import edu.mum.eshop.domain.users.Role;
import edu.mum.eshop.domain.users.User;

public interface UsersService {
    public User saveUuyer(User user);
    public Role getRole(String type);
}
