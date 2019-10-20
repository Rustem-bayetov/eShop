package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.users.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, Integer> {
}
