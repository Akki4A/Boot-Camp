package com.incs83.hrm.repository;

import com.incs83.hrm.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository <Role, String> {
}
