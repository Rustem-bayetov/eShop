package edu.mum.eshop.controllers;
import edu.mum.eshop.services.NotificationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationController {
    @Autowired
    NotificationSerivce notificationSerivce;

}
