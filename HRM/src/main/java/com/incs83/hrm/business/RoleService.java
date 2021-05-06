package com.incs83.hrm.business;

import com.incs83.hrm.common.RoleRequest;
import com.incs83.hrm.entities.Address;
import com.incs83.hrm.entities.Role;
import com.incs83.hrm.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Role getRoleById(Integer id){
        return roleRepository.findById(id).orElse(null);
    }

    public void updateRole(RoleRequest roleRequest,Integer id){
        Role existingRole = roleRepository.findById(id).orElse(null);
        if (existingRole != null) {
            existingRole.setName(roleRequest.getName());
            existingRole.setDescription(roleRequest.getDescription());
            existingRole.setPermission(roleRequest.getPermission());
            roleRepository.save(existingRole);
        }
    }
    public void deleteRoleById(Integer id){
        roleRepository.deleteById(id);
    }
}
