package com.krglow.taskmanager.service.task.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.krglow.taskmanager.entity.task.TaskEntity;
import com.krglow.taskmanager.entity.task.VTaskEntity;
import com.krglow.taskmanager.service.task.TaskStatus;
import com.krglow.taskmanager.service.task.dto.TaskDto;
import com.krglow.taskmanager.service.task.dto.VTaskDto;


@Mapper(componentModel = "spring")
public interface TaskMapper {

    VTaskDto toVTaskDto(VTaskEntity source);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "assignedUsers", ignore = true)
    @Mapping(source = "statusId", target = "status", qualifiedByName = "statusIdToStatus")
    TaskEntity toTaskEntity(TaskDto source);

    @Named("statusIdToStatus")
    default TaskStatus statusIdToStatus(Long statusId) {
        return TaskStatus.findById(statusId);
    }

}
