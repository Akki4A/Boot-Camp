package com.bootcamp.hrm.repository;

import com.bootcamp.hrm.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DepartmentRepository extends JpaRepository<Department, UUID> {
}
