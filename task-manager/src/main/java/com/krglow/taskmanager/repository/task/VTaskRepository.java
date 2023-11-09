package com.krglow.taskmanager.repository.task;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krglow.taskmanager.entity.task.VTaskEntity;


public interface VTaskRepository extends JpaRepository<VTaskEntity, Long> {

}
