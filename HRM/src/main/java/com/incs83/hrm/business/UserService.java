package com.incs83.hrm.business;

import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.entities.User;
import com.incs83.hrm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void createUser(UserRequest userRequest) {
        User user = new User();
//        user.setId(userRequest.getId());
        user.setFirstName(userRequest.getFirstName());
        user.setLastName(userRequest.getLastName());
        user.setGender(userRequest.getGender());
        user.setDateOfBirth(userRequest.getDateOfBirth());
        user.setEmail(userRequest.getEmail());
        user.setPhoneNumber(userRequest.getPhoneNumber());
        userRepository.save(user);
    }

    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }

    public void updateUser(UserRequest userRequest, Integer id) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setFirstName(userRequest.getFirstName());
            existingUser.setLastName(userRequest.getLastName());
            existingUser.setGender(userRequest.getGender());
            existingUser.setDateOfBirth(userRequest.getDateOfBirth());
            existingUser.setEmail(userRequest.getEmail());
            existingUser.setPhoneNumber(userRequest.getPhoneNumber());
            userRepository.save(existingUser);
        }


    }

    public void deleteUserById(Integer id) {
        userRepository.deleteById(id);
    }

}
