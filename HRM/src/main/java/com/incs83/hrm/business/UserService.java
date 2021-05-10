package com.incs83.hrm.business;

import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.entities.User;
import com.incs83.hrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequest userRequest) {
        User user = new User();
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        user.setCreatedAt(new Timestamp(new Date().getTime()));
        user.setCreatedBy("Dev_Department");
        user.setAddress(userRequest.getAddress());
        user.setDepartments(userRequest.getDepartments());
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(UserRequest userRequest, UUID id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(userRequest.getFirstName());
            existingUser.setLastName(userRequest.getLastName());
            existingUser.setGender(userRequest.getGender());
            existingUser.setDateOfBirth(userRequest.getDateOfBirth());
            existingUser.setEmail(userRequest.getEmail());
            existingUser.setPhoneNumber(userRequest.getPhoneNumber());
            existingUser.setUpdatedAt(new Timestamp(new Date().getTime()));
            existingUser.setUpdatedBy("Dev_Department");
            existingUser.setAddress(userRequest.getAddress());
            existingUser.setDepartments(userRequest.getDepartments());
            userRepository.save(existingUser);
        }


    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

}
