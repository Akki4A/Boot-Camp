package com.incs83.hrm.business;

import com.incs83.hrm.common.RoleRequest;
import com.incs83.hrm.entities.Role;
import com.incs83.hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        roleRepository.save(role);
    }

    public List<Role> getAllRole(){
        return roleRepository.findAll();
    }

    public Role getRoleById(UUID id){
        return roleRepository.findById(id).orElse(null);
    }

    public void updateRole(RoleRequest roleRequest,UUID id){
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (existingRole != null) {
            existingRole.setName(roleRequest.getName());
            existingRole.setDescription(roleRequest.getDescription());
            existingRole.setPermission(roleRequest.getPermission());
            roleRepository.save(existingRole);
        }
    }
    public void deleteRoleById(UUID id){
        roleRepository.deleteById(id);
    }
}
