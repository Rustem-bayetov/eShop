package edu.mum.eshop.repositories;
import edu.mum.eshop.domain.users.RejectedUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RejectedUserRepository extends CrudRepository<RejectedUser, Integer> {
}
