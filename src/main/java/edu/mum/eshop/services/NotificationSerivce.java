package edu.mum.eshop.services;

import edu.mum.eshop.domain.notification.Notification;
import sun.management.snmp.jvminstr.NotificationTargetImpl;

import java.util.List;

public interface NotificationSerivce {
    public Notification save(Notification notification);
    public List<Notification> findAllByUserId(Integer uid);
    public Notification getById(Integer id);
    public void markNotificationRead(Notification notification);
    public List<Notification> findUnReadByUserId(Integer uid);
}
