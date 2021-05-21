package com.bootcamp.hrm.repository;

import com.bootcamp.hrm.entities.UserLogin;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDaoRepository extends CrudRepository<UserLogin, Integer> {
    UserLogin findByUsername(String username);
}
