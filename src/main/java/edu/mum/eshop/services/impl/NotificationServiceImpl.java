package edu.mum.eshop.services.impl;
import edu.mum.eshop.domain.notification.Notification;
import edu.mum.eshop.domain.notification.NotificationStatus;
import edu.mum.eshop.repositories.NotificationRepository;
import edu.mum.eshop.services.NotificationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationSerivce {
    @Autowired NotificationRepository notificationRepository;
    @Override public Notification save(Notification notification) { return notificationRepository.save(notification); }
    @Override public List<Notification> findAllByUserId(Integer uid) { return notificationRepository.findAllByUserId(uid); }
    @Override public Notification getById(Integer id){return  notificationRepository.findById(id).get();}
    @Override public void markNotificationRead(Notification notification) {
        notification.setNotificationStatus(NotificationStatus.READ);
        System.out.println(notification);
        notificationRepository.save(notification); }
    @Override public List<Notification> findUnReadByUserId(Integer uid){ return notificationRepository.findUnReadByUserId(uid); }
}
