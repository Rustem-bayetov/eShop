package edu.mum.eshop.services;
import edu.mum.eshop.domain.User;
import java.util.List;
public interface UserService {
    List<User> getAll();
    void save(User user);
    User getById(Integer id);
}