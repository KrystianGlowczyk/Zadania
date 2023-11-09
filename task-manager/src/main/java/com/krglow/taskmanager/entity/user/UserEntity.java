package com.krglow.taskmanager.entity.user;

import java.util.HashSet;
import java.util.Set;

import com.krglow.taskmanager.entity.DeletableEntity;
import com.krglow.taskmanager.entity.task.TaskEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "USERS")
public class UserEntity extends DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    private String email;

    @ManyToMany(mappedBy = "assignedUsers")
    private Set<TaskEntity> tasks = new HashSet<>();

}
