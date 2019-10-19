package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.User;
import edu.mum.eshop.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {
    @Autowired UserService userService;
    @Override public List<User> getAll() { return userService.getAll(); }
    @Override public void save(User user) { userService.save(user); }
    @Override public User getById(Integer id) { return userService.getById(id); }
}
