package com.bootcamp.hrm.controller;

import com.bootcamp.hrm.business.DepartmentService;
import com.bootcamp.hrm.common.DepartmentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.LinkedHashMap;
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
    public List<HashMap<String, Object>> getAllDepartment() {
        return departmentService.getAllDepartment();
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public LinkedHashMap<String, Object> getDepartmentById(@PathVariable UUID id) {
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

    @RequestMapping(method = RequestMethod.DELETE)
    public void deleteAllDepartment() {
        departmentService.deleteAllDepartment();
    }
}
