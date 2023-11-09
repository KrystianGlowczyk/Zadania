package com.krglow.taskmanager.entity.task;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import com.krglow.taskmanager.entity.DeletableEntity;
import com.krglow.taskmanager.entity.user.UserEntity;
import com.krglow.taskmanager.service.task.TaskStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "TASKS")
public class TaskEntity extends DeletableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status_id")
    private TaskStatus status;

    private LocalDateTime executeTime;

    @ManyToMany
    @JoinTable(name = "task_user", joinColumns = @JoinColumn(name = "task_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<UserEntity> assignedUsers = new HashSet<>();

}
