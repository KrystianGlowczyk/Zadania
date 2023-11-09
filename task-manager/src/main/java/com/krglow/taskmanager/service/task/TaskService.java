package com.krglow.taskmanager.service.task;

import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.krglow.taskmanager.service.task.dto.TaskDto;
import com.krglow.taskmanager.service.task.dto.VTaskDto;


public interface TaskService {

    Page<VTaskDto> getAllTasks(Pageable pageable);

    VTaskDto read(Long id);

    Long create(TaskDto dto);

    Long update(Long id, TaskDto dto);

    void delete(Long id);

    void changeStatus(Long newStatusId, Long taskId);

    void assignUsers(Long taskId, Set<Long> userIds);

    void removeUserFromTask(Long taskId, Long userId);

    void assignUserToTask(Long taskId, Long userId);

}
