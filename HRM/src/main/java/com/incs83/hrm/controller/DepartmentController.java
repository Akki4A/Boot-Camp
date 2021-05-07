package com.incs83.hrm.controller;

import com.incs83.hrm.business.DepartmentService;
import com.incs83.hrm.common.DepartmentRequest;
import com.incs83.hrm.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(method = RequestMethod.POST)
    public void createDepartment(@RequestBody DepartmentRequest departmentRequest) {
        departmentService.createDepartment(departmentRequest);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Department> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public Department getDepartmentById(@PathVariable UUID id) {
        return departmentService.getDepartmentById(id);
    }

    @RequestMapping(path = "{id}", method = RequestMethod.PUT)
    public void updateDepartmentByID(@RequestBody DepartmentRequest departmentRequest, @PathVariable UUID id) {
        departmentService.updateDepartment(departmentRequest, id);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public void deleteDepartmentById(@PathVariable UUID id) {
        departmentService.deleteDepartmentById(id);
    }
}
