package com.bootcamp.hrm.business;

import com.bootcamp.hrm.entities.Department;
import com.bootcamp.hrm.entities.User;
import com.bootcamp.hrm.repository.DepartmentRepository;
import com.bootcamp.hrm.repository.UserRepository;
import com.bootcamp.hrm.common.UserRequest;
import com.bootcamp.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

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
        mapToDepartment(userRequest.getDepartmentIds(), user);

        userRepository.save(user);
    }


    public List<HashMap<String, Object>> getAllUser() {
        List<User> users = userRepository.findAll();
        List<HashMap<String, Object>> userRequestList = new ArrayList<>();
        for (User user : users) {
            userRequestList.add(setUserResponse(user));
        }
        return userRequestList;
    }

    public LinkedHashMap<String, Object> getUserById(UUID id) {
        User user = userRepository.findById(id).orElse(new User());
        return setUserResponse(user);
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
        existingUser.setUpdatedAt(CommonUtils.getCurrentTime());
        existingUser.setUpdatedBy("Dev_Department");
        userRepository.save(existingUser);
    }

    public void deleteUserById(UUID id) {
        userRepository.deleteById(id);
    }

    public void deleteAllUser() {
        userRepository.deleteAll();
    }


    public void mapToDepartment(Set<UUID> departmentIds, User user) {
        for (UUID deptId : departmentIds) {
            Department department = departmentRepository.findById(deptId).get();
            department.getUser().add(user);
            user.getDepartments().add(department);
            CommonUtils.createAudit(department);
        }
    }

    public LinkedHashMap<String, Object> setUserResponse(User user) {
        LinkedHashMap<String, Object> setUserResp = new LinkedHashMap<>();
        setUserResp.put("id", user.getId());
        setUserResp.put("full name", user.getFirstName() + " " + user.getLastName());
        setUserResp.put("d.o.b", user.getDateOfBirth());
        setUserResp.put("email", user.getEmail());
        setUserResp.put("gender", user.getGender());
        setUserResp.put("contact", user.getPhoneNumber());
        LinkedHashMap<String, Object> setAddress = new LinkedHashMap<>();
        setAddress.put("house no.", user.getAddress().getHouseNumber());
        setAddress.put("landmark", user.getAddress().getColony());
        setAddress.put("city", user.getAddress().getCity());
        setAddress.put("pin code", user.getAddress().getPinCode());
        setAddress.put("state", user.getAddress().getState());
        setUserResp.put("address", setAddress);
        List<HashMap<String, Object>> deptList = new ArrayList<>();
        for (Department department : user.getDepartments()) {
            LinkedHashMap<String, Object> setDept = new LinkedHashMap<>();
            setDept.put("department name", department.getName());
            setDept.put("department description", department.getDescription());
            deptList.add(setDept);
        }
        setUserResp.put("departments", deptList);
        return setUserResp;
    }
}
