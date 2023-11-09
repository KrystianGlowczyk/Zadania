package com.krglow.taskmanager.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "V_USERS")
public class VUserEntity {

    @Id
    private Long id;

    private String name;

    private String surname;

    private String email;

}
