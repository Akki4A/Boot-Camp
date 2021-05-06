package com.incs83.hrm.repository;

import com.incs83.hrm.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository <Department, Integer> {
}
