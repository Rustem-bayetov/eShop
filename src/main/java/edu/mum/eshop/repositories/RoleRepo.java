package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.users.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo  extends CrudRepository<Role, Integer> {
    Role findByType(String type);
}
