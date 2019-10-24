package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.users.RejectedUser;
import edu.mum.eshop.domain.users.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);

    @Query(value = "SELECT * FROM user WHERE active = 0 and user_id not in (select user_id from rejected_user)", nativeQuery = true)
    List<User> getPendingApprovalUsers();
    @Transactional @Modifying
    @Query(value = "DELETE FROM user_followed_sellers where user_user_id = ?1 and followed_sellers_user_id= ?2", nativeQuery = true)
    void unfollowSellerDB(Integer buyerId, Integer sellerId);
}
