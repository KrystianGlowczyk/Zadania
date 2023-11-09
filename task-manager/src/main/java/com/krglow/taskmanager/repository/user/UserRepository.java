package com.krglow.taskmanager.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krglow.taskmanager.entity.user.UserEntity;


public interface UserRepository extends JpaRepository<UserEntity, Long> {

}
