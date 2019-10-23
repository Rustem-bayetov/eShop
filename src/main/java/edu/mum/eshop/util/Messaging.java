package edu.mum.eshop.util;

import edu.mum.eshop.domain.notification.Notification;
import edu.mum.eshop.domain.notification.NotificationChannel;
import edu.mum.eshop.domain.notification.NotificationStatus;
import edu.mum.eshop.domain.users.User;
import edu.mum.eshop.services.NotificationSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class Messaging {
    @Autowired static NotificationSerivce notificationSerivce;

    @Autowired private static JavaMailSender emailSender;

    private static void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            emailSender.send(message);
        }
        catch (MailException e){
            System.out.println("Mail Exception, Cause: " + e.getCause());
            System.out.println("Mail Exception, Message: "+ e.getMessage());
        }
    }

    public static Notification send(User receiver, String title, String body, NotificationChannel channel){
        Notification notification = new Notification();
        notification.setBody(body);
        notification.setTitle(title);
        notification.setReceiver(receiver);
        notification.setNotificationStatus(NotificationStatus.CREATED);
        notification.setNotificationChannel(channel);
        Notification savedNotification = notificationSerivce.save(notification);
        if(channel == NotificationChannel.WEB){
            // save to user messages

        }else if(channel == NotificationChannel.BOTH){
            // send email
            sendEmail(receiver.getEmail(), title, body);
            // save web notification copy

        }else if (channel == NotificationChannel.EMAIL){
            // send email
            sendEmail(receiver.getEmail(), title, body);
            // check email status

        }
        return savedNotification;
    }
}
