package com.incs83.hrm.repository;

import com.incs83.hrm.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer> {

}
