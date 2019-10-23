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
import org.springframework.stereotype.Component;

@Component
public class Messaging {
    @Autowired  NotificationSerivce notificationSerivce;

    @Autowired private JavaMailSender emailSender;

    private void sendEmail(String receiverEmail, String subject, String messageBody) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(receiverEmail);
        message.setSubject(subject);
        message.setText(messageBody);
        try { emailSender.send(message); }
        catch (MailException e){
            System.out.println("Mail Exception, Cause: " + e.getCause());
            System.out.println("Mail Exception, Message: "+ e.getMessage());
        }
    }
    /*saved notifications are supposed to be alwyas visible for user in his profile */

    /**
     * @param receiver is used to save the notification in user's notifications and get the email to the user
     * @param body this the actual body of the notification to be sent
     * @param channel use NotificationVhannel.BOTH to trigger email sending as well
     * @param title the subject of the email and the title of the notification on the web portal
     * */
    public Notification send(User receiver, String title, String body, NotificationChannel channel){
        Notification notification = new Notification();
        notification.setBody(body);
        notification.setTitle(title);
        notification.setReceiver(receiver);
        notification.setNotificationStatus(NotificationStatus.CREATED);
        notification.setNotificationChannel(channel);
        // if both, send email
        if(channel == NotificationChannel.BOTH){
            sendEmail(receiver.getEmail(), title, body);
            notification.setNotificationStatus(NotificationStatus.SENT);
        }
        //saving web version
        Notification savedNotification = notificationSerivce.save(notification);
        return savedNotification;
    }
}
