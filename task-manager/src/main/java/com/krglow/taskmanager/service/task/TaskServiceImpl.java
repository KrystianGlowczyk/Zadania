package com.krglow.taskmanager.service.task;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.krglow.taskmanager.repository.task.TaskRepository;
import com.krglow.taskmanager.repository.task.VTaskRepository;
import com.krglow.taskmanager.repository.user.UserRepository;
import com.krglow.taskmanager.service.task.dto.TaskDto;
import com.krglow.taskmanager.service.task.dto.VTaskDto;
import com.krglow.taskmanager.service.task.mapper.TaskMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private static final String TASK_NOT_FOUND_MESSAGE = "Nie znaleziono zadania.";
    private static final String USER_NOT_FOUND_MESSAGE = "Nie znaleziono użytkownika.";

    private final TaskMapper taskMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final VTaskRepository vTaskRepository;

    @Override
    public Page<VTaskDto> getAllTasks(Pageable pageable) {
        return vTaskRepository.findAll(pageable).map(taskMapper::toVTaskDto);
    }

    @Override
    public VTaskDto read(Long id) {
        return vTaskRepository.findById(id).map(taskMapper::toVTaskDto).orElse(null);
    }

    @Override
    @Transactional
    public Long create(TaskDto dto) {
        return taskRepository.save(taskMapper.toTaskEntity(dto)).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, TaskDto dto) {
        if (id == null) {
            throw new IllegalArgumentException("Id nie może być NULL");
        }
        dto.setId(id);
        return taskRepository.save(taskMapper.toTaskEntity(dto)).getId();

    }

    @Override
    @Transactional
    public void delete(Long id) {
        taskRepository.findById(id).ifPresentOrElse(taskEntity -> {
            taskEntity.setDeleted(true);
            taskRepository.save(taskEntity);
        }, () -> {
            throw new NotFoundException(TASK_NOT_FOUND_MESSAGE);
        });
    }

    @Override
    @Transactional
    public void changeStatus(Long newStatusId, Long taskId) {
        var status = TaskStatus.findById(newStatusId);

        taskRepository.findById(taskId).ifPresentOrElse(task -> {
            task.setStatus(status);
            taskRepository.save(task);
        }, () -> {
            throw new NotFoundException(TASK_NOT_FOUND_MESSAGE);
        });

    }

    @Override
    @Transactional
    public void assignUsers(Long taskId, Set<Long> userIds) {
        var taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND_MESSAGE));

        var users = userIds.stream().map(id -> userRepository.findById(id).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE + " o ID: " + id))).collect(Collectors.toSet());

        var currentAssignedUsers = taskEntity.getAssignedUsers();

        currentAssignedUsers.addAll(users);

        taskEntity.setAssignedUsers(currentAssignedUsers);

        taskRepository.save(taskEntity);
    }

    @Override
    @Transactional
    public void removeUserFromTask(Long taskId, Long userId) {
        var taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND_MESSAGE));

        var userToRemove = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));

        taskEntity.getAssignedUsers().remove(userToRemove);

        taskRepository.save(taskEntity);
    }

    @Override
    @Transactional
    public void assignUserToTask(Long taskId, Long userId) {
        var taskEntity = taskRepository.findById(taskId).orElseThrow(() -> new NotFoundException(TASK_NOT_FOUND_MESSAGE));

        var userToAssign = userRepository.findById(userId).orElseThrow(() -> new NotFoundException(USER_NOT_FOUND_MESSAGE));

        taskEntity.getAssignedUsers().add(userToAssign);

        taskRepository.save(taskEntity);
    }

}
