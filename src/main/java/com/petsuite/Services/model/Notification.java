package com.petsuite.Services.model;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "notification")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer notification_id;

    @NotBlank
    private String notification_subject;

    @NotBlank
    private String notification_description;

    @NotBlank
    private String notification_status;

    @NotNull
    private String user;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name="user", nullable=false,updatable = false, insertable = false)
    private InfoUser infoUser_d;

}
