package com.krglow.taskmanager.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krglow.taskmanager.entity.task.TaskEntity;


public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
