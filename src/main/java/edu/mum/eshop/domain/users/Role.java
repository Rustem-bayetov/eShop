package edu.mum.eshop.domain.users;

import lombok.Data;
import javax.persistence.*;


@Data
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer id;

    @Column(name = "role")
    private String type;
}
