package com.incs83.hrm.repository;

import com.incs83.hrm.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository <Role, UUID> {
}
