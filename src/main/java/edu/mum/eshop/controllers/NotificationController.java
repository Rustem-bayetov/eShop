package edu.mum.eshop.controllers;
import edu.mum.eshop.domain.notification.NotificationChannel;
import edu.mum.eshop.services.NotificationSerivce;
import edu.mum.eshop.services.UsersService;
import edu.mum.eshop.util.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/notification")
@Controller
public class NotificationController {
    @Autowired NotificationSerivce notificationSerivce;
    @Autowired UsersService usersService;
    @Autowired Messaging messaging;
    @GetMapping("/testWeb")
    public void testWebNotifications(){
        messaging.send(usersService.getUserById(1), "title","body", NotificationChannel.WEB);
    }
    @GetMapping("/testBoth")
    public void testBothNotifications(){
        messaging.send(usersService.getUserById(1), "title","body", NotificationChannel.BOTH);
    }
}
