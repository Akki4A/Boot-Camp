package com.bootcamp.hrm.repository;

import com.bootcamp.hrm.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {

    Optional<Login> findByUserName(String userName);
}
