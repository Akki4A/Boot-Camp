package com.bootcamp.hrm.controller;

import com.bootcamp.hrm.business.RoleService;
import com.bootcamp.hrm.common.RoleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @RequestMapping(method = RequestMethod.POST)
    public void createDepartment(@RequestBody RoleRequest roleRequest) {
        roleService.createRole(roleRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<HashMap<String, Object>> getAllRole() {
        return roleService.getAllRole();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public LinkedHashMap<String, Object> getRoleById(@PathVariable UUID id) {
        return roleService.getRoleById(id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateDepartmentByID(@RequestBody RoleRequest roleRequest, @PathVariable UUID id) {
        roleService.updateRole(roleRequest, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteRoleById(@PathVariable UUID id) {
        roleService.deleteRoleById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllRole() {
        roleService.deleteAllRole();
    }
}
