package com.incs83.hrm.business;

import com.incs83.hrm.common.UserRequest;
import com.incs83.hrm.entities.Department;
import com.incs83.hrm.entities.User;
import com.incs83.hrm.repository.DepartmentRepository;
import com.incs83.hrm.repository.UserRepository;
import com.incs83.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
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


    public void mapToDepartment(Set<UUID> departmentIds, User user) {
        for (UUID deptId : departmentIds) {
            Department department = departmentRepository.findById(deptId).get();
            department.getUser().add(user);
            user.getDepartments().add(department);
            CommonUtils.createAudit(department);
        }
    }

    public LinkedHashMap<String, Object> setUserResponse(User user) {
        LinkedHashMap<String, Object> setResp = new LinkedHashMap<>();
        setResp.put("id", user.getId());
        setResp.put("full name", user.getFirstName() + " " + user.getLastName());
        setResp.put("d.o.b", user.getDateOfBirth());
        setResp.put("email", user.getEmail());
        setResp.put("gender", user.getGender());
        setResp.put("contact", user.getPhoneNumber());
        LinkedHashMap<String, Object> setAddress = new LinkedHashMap<>();
        setAddress.put("house no.", user.getAddress().getHouseNumber());
        setAddress.put("landmark", user.getAddress().getColony());
        setAddress.put("city", user.getAddress().getCity());
        setAddress.put("pin code", user.getAddress().getPinCode());
        setAddress.put("state", user.getAddress().getState());
        setResp.put("address", setAddress);
        List<HashMap<String, Object>> deptList = new ArrayList<>();
        for (Department department : user.getDepartments()) {
            LinkedHashMap<String, Object> setDept = new LinkedHashMap<>();
            setDept.put("department name", department.getName());
            setDept.put("department description", department.getDescription());
            deptList.add(setDept);
        }
        setResp.put("departments", deptList);
        return setResp;
    }
}
