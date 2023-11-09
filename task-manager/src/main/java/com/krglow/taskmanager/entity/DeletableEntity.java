package com.krglow.taskmanager.entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@MappedSuperclass
public class DeletableEntity {

    private boolean deleted;

}
