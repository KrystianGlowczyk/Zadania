package com.krglow.taskmanager.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.krglow.taskmanager.service.user.dto.UserDto;
import com.krglow.taskmanager.service.user.dto.VUserDto;


public interface UserService {

    Page<VUserDto> getAllUsers(Pageable pageable);

    VUserDto read(Long id);

    Long create(UserDto dto);

    Long update(Long id, UserDto dto);

    void delete(Long id);

}
