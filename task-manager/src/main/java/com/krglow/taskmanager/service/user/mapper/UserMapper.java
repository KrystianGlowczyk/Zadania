package com.krglow.taskmanager.service.user.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.krglow.taskmanager.entity.user.UserEntity;
import com.krglow.taskmanager.entity.user.VUserEntity;
import com.krglow.taskmanager.service.user.dto.UserDto;
import com.krglow.taskmanager.service.user.dto.VUserDto;


@Mapper(componentModel = "spring")
public interface UserMapper {

    VUserDto toVUserDto(VUserEntity source);

    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    UserEntity toUserEntity(UserDto source);

}
