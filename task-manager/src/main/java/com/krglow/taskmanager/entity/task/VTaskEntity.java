package com.krglow.taskmanager.entity.task;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name = "V_TASKS")
public class VTaskEntity {

    @Id
    private Long id;

    private String title;

    private String description;

    private Long statusId;

    private String statusName;

    private LocalDateTime executeTime;

    private String assignedUsers;

}
