package com.incs83.hrm.business;

import com.incs83.hrm.common.RoleRequest;
import com.incs83.hrm.entities.Role;
import com.incs83.hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public void createRole(RoleRequest roleRequest){
        Role role = new Role();
        role.setName(roleRequest.getName());
        role.setDescription(roleRequest.getDescription());
        role.setPermission(roleRequest.getPermission());
        role.setCreatedAt(new Timestamp(new Date().getTime()));
        role.setCreatedBy("Dev_Department");
        role.setUsers(roleRequest.getUsers());
        roleRepository.save(role);
    }

    public List<RoleRequest> getAllRole() {
        List<Role> roles = roleRepository.findAll();
        List<RoleRequest> roleRequestList = new ArrayList<>();
        for (Role role : roles) {
            RoleRequest roleRequest = new RoleRequest();
            roleRequest.setId(role.getId());
            roleRequest.setName(role.getName());
            roleRequest.setDescription(role.getDescription());
            roleRequest.setPermission(role.getPermission());
            roleRequest.setUsers(role.getUsers());
            roleRequestList.add(roleRequest);
        }
        return roleRequestList;
    }

    public RoleRequest getRoleById(UUID id){
        Role role = roleRepository.findById(id).orElse(new Role());
        RoleRequest roleRequest = new RoleRequest();
        roleRequest.setId(role.getId());
        roleRequest.setName(role.getName());
        roleRequest.setDescription(role.getDescription());
        roleRequest.setPermission(role.getPermission());
        roleRequest.setUsers(role.getUsers());
        return roleRequest;
    }

    public void updateRole(RoleRequest roleRequest,UUID id){
        Role existingRole = roleRepository.findById(id).orElse(null);
        assert existingRole != null;
        existingRole.setName(roleRequest.getName());
        existingRole.setDescription(roleRequest.getDescription());
        existingRole.setPermission(roleRequest.getPermission());
        existingRole.setUpdatedAt(new Timestamp(new Date().getTime()));
        existingRole.setUpdatedBy("Dev_Department");
        existingRole.setUsers(roleRequest.getUsers());
        roleRepository.save(existingRole);
    }
    public void deleteRoleById(UUID id){
        roleRepository.deleteById(id);
    }

    public void deleteAllRole(){ roleRepository.deleteAll();}
}
