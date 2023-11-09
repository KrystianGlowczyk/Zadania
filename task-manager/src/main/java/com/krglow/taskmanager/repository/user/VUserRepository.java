package com.krglow.taskmanager.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import com.krglow.taskmanager.entity.user.VUserEntity;


public interface VUserRepository extends JpaRepository<VUserEntity, Long> {

}
