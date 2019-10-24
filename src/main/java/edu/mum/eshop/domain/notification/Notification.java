package edu.mum.eshop.domain.notification;

import edu.mum.eshop.domain.users.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String title;

    @NotNull
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User receiver;

    @NotNull
    private NotificationChannel notificationChannel;

    @NotNull
    private NotificationStatus notificationStatus;
}
