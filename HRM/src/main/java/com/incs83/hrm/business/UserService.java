package com.incs83.hrm.business;

import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.entities.Department;
import com.incs83.hrm.entities.User;
import com.incs83.hrm.repository.UserRepository;
import com.incs83.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
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
        user.setCreatedAt(CommonUtils.getCurrentTime());
        user.setCreatedBy("Dev_Department");
        user.setAddress(userRequest.getAddress());
        CommonUtils.createAudit(userRequest.getAddress());
        user.setDepartments(userRequest.getDepartment());
        for (Department department : userRequest.getDepartment()) {
            CommonUtils.createAudit(department);
        }
        userRepository.save(user);
    }


    public List<UserRequest> getAllUser() {
        List<User> users = userRepository.findAll();
        List<UserRequest> userRequestList = new ArrayList<>();
        for (User user : users) {
            UserRequest userRequest = new UserRequest();
            userRequest.setId(user.getId());
            userRequest.setFirstName(user.getFirstName());
            userRequest.setLastName(user.getLastName());
            userRequest.setGender(user.getGender());
            userRequest.setEmail(user.getEmail());
            userRequest.setDateOfBirth(user.getDateOfBirth());
            userRequest.setPhoneNumber(user.getPhoneNumber());
            userRequest.setAddress(user.getAddress());
            userRequest.setDepartment(user.getDepartments());
            userRequestList.add(userRequest);
        }
        return userRequestList;
    }

    public UserRequest getUserById(UUID id) {
        User user = userRepository.findById(id).orElse(new User());
        UserRequest userRequest = new UserRequest();
        userRequest.setId(user.getId());
        userRequest.setFirstName(user.getFirstName());
        userRequest.setLastName(user.getLastName());
        userRequest.setGender(user.getGender());
        userRequest.setEmail(user.getEmail());
        userRequest.setDateOfBirth(user.getDateOfBirth());
        userRequest.setPhoneNumber(user.getPhoneNumber());
        return userRequest;
    }

    public void updateUser(UserRequest userRequest, UUID id) {
        User existingUser = userRepository.findById(id).orElse(null);
        assert existingUser != null;
        existingUser.setFirstName(userRequest.getFirstName());
        existingUser.setLastName(userRequest.getLastName());
        existingUser.setGender(userRequest.getGender());
        existingUser.setDateOfBirth(userRequest.getDateOfBirth());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPhoneNumber(userRequest.getPhoneNumber());
        existingUser.setUpdatedAt(new Timestamp(new Date().getTime()));
        existingUser.setUpdatedBy("Dev_Department");
        existingUser.setAddress(userRequest.getAddress());
        userRepository.save(existingUser);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }
}
