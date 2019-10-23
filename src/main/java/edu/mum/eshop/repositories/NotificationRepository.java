package edu.mum.eshop.repositories;

import edu.mum.eshop.domain.notification.Notification;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    @Query("select n from Notification n where user_id = :uid")
    public List<Notification> findAllByUserId(Integer uid);
}
