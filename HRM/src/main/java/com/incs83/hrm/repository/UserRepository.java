package com.incs83.hrm.repository;

import com.incs83.hrm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository <User,UUID> {
}
