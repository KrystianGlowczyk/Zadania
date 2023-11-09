package com.krglow.taskmanager.service.user;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import com.krglow.taskmanager.repository.user.UserRepository;
import com.krglow.taskmanager.repository.user.VUserRepository;
import com.krglow.taskmanager.service.user.dto.UserDto;
import com.krglow.taskmanager.service.user.dto.VUserDto;
import com.krglow.taskmanager.service.user.mapper.UserMapper;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final VUserRepository vUserRepository;

    @Override
    public Page<VUserDto> getAllUsers(Pageable pageable) {
        return vUserRepository.findAll(pageable).map(userMapper::toVUserDto);
    }

    @Override
    public VUserDto read(Long id) {
        return vUserRepository.findById(id).map(userMapper::toVUserDto).orElse(null);
    }

    @Override
    @Transactional
    public Long create(UserDto dto) {
        return userRepository.save(userMapper.toUserEntity(dto)).getId();
    }

    @Override
    @Transactional
    public Long update(Long id, UserDto dto) {
        if (id == null) {
            throw new IllegalArgumentException("Id nie może być NULL");
        }
        return userRepository.save(userMapper.toUserEntity(dto)).getId();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.findById(id).ifPresentOrElse(userEntity -> {
            userEntity.setDeleted(true);
            userRepository.save(userEntity);
        }, () -> {
            throw new NotFoundException("Nie znaleziono uźytkownika");
        });

    }

}
