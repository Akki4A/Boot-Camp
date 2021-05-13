package com.incs83.hrm.business;

import com.incs83.hrm.common.RoleRequest;
import com.incs83.hrm.entities.Role;
import com.incs83.hrm.entities.User;
import com.incs83.hrm.repository.RoleRepository;
import com.incs83.hrm.repository.UserRepository;
import com.incs83.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    public void createRole(RoleRequest roleRequest){
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role.setPermission(roleRequest.getPermission());
        role.setCreatedAt(CommonUtils.getCurrentTime());
        role.setCreatedBy("Dev_Department");
        mapToUser(roleRequest.getUserIds(), role);
        roleRepository.save(role);
    }

    public List<HashMap<String, Object>> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        List<HashMap<String, Object>> roleRequestList = new ArrayList<>();
        for (Role role : roles) {
            roleRequestList.add(setRoleResponse(role));
        }
        return roleRequestList;
    }

public LinkedHashMap<String, Object> getRoleById(UUID id) {
    Role role =roleRepository.findById(id).orElse(new Role());
    return setRoleResponse(role);
}

    public LinkedHashMap<String, Object> setRoleResponse(Role role){
        LinkedHashMap<String, Object > setRoleResp = new LinkedHashMap<>();
        setRoleResp.put("id", role.getId());
        setRoleResp.put("role name", role.getName());
        setRoleResp.put("description", role.getDescription());
        setRoleResp.put("permission", role.getPermission());
        List<HashMap<String, Object>> userList = new ArrayList<>();
        for (User user : role.getUsers()){
            LinkedHashMap<String, Object> setUser = new LinkedHashMap<>();
            setUser.put("user name", user.getFirstName() + " " + user.getLastName());
            setUser.put("phone number", user.getPhoneNumber());
            setUser.put("email", user.getEmail());
            userList.add(setUser);
        }
        setRoleResp.put("users", userList);
        return setRoleResp;
    }

    public void updateRole(RoleRequest roleRequest,UUID id){
        Role existingRole = roleRepository.findById(id).orElse(null);
        assert existingRole != null;
        existingRole.setName(roleRequest.getName());
        existingRole.setDescription(roleRequest.getDescription());
        existingRole.setPermission(roleRequest.getPermission());
        existingRole.setUpdatedAt(CommonUtils.getCurrentTime());
        existingRole.setUpdatedBy("Dev_Department");
        roleRepository.save(existingRole);
    }
    public void deleteRoleById(UUID id){
        roleRepository.deleteById(id);
    }

    public void deleteAllRole(){ roleRepository.deleteAll();}

    public void mapToUser(Set<UUID> userIds, Role role) {
        for (UUID userId : userIds) {
            User user = userRepository.findById(userId).get();
            user.getRole().add(role);
            role.getUsers().add(user);
            CommonUtils.createAudit(user);
        }
    }
}
