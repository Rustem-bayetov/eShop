package edu.mum.eshop.controllers;
import edu.mum.eshop.domain.ads.AdRequest;
import edu.mum.eshop.domain.ads.Decision;
import edu.mum.eshop.domain.notification.Notification;
import edu.mum.eshop.domain.notification.NotificationChannel;
import edu.mum.eshop.domain.notification.NotificationStatus;
import edu.mum.eshop.domain.product.Product;
import edu.mum.eshop.services.NotificationSerivce;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.util.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RequestMapping("/notification")
@Controller
public class NotificationController extends BaseController{
    @Autowired NotificationSerivce notificationSerivce;
    @Autowired UsersService usersService;
    @Autowired Messaging messaging;
    @GetMapping("/testWeb")
    public void testWebNotifications(){
        messaging.send(usersService.getUserById(getUser().getId()), "title","body", NotificationChannel.WEB);
    }
    @GetMapping("/testBoth")
    public void testBothNotifications(){
        messaging.send(usersService.getUserById(getUser().getId()), "title","body", NotificationChannel.BOTH);
    }
    @GetMapping("/web")
    public String getMyNotification(Model model){
        List<Notification> notifications = notificationSerivce.findAllByUserId(getUser().getId());
        model.addAttribute("notifications", notifications);
        return "notifications";
    }

    @GetMapping("{notificationid}/READ")
    public String approveAd(@PathVariable("notificationid") Integer id, Model model) {
        Notification notification = notificationSerivce.getById(id);
        if (notification.getNotificationStatus()== NotificationStatus.CREATED || notification.getNotificationStatus() == NotificationStatus.SENT) {
            System.out.println("READ");
            notificationSerivce.markNotificationRead(notification);
        }
        return "redirect:/notification/web";
    }
}