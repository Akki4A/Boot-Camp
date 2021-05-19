package com.bootcamp.hrm.business;

import com.bootcamp.hrm.repository.DepartmentRepository;
import com.bootcamp.hrm.common.DepartmentRequest;
import com.bootcamp.hrm.entities.Department;
import com.bootcamp.hrm.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public void createDepartment(DepartmentRequest departmentRequest) {
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
        department.setCreatedAt(CommonUtils.getCurrentTime());
        department.setCreatedBy("Dev_Department");
        departmentRepository.save(department);
    }

    public List<HashMap<String, Object>> getAllDepartment() {
        List<Department> departments = departmentRepository.findAll();
        List<HashMap<String, Object>> departmentRequestList = new ArrayList<>();
        for (Department department : departments) {
            departmentRequestList.add(setDepartmentResponse(department));
        }
        return departmentRequestList;
    }

    public LinkedHashMap<String, Object> getDepartmentById(UUID id) {
        Department department = departmentRepository.findById(id).orElse(new Department());
        return setDepartmentResponse(department);
    }

    public void updateDepartment(DepartmentRequest departmentRequest, UUID id) {
        Department existingDepartment = departmentRepository.findById(id).get();
        existingDepartment.setName(departmentRequest.getName());
        existingDepartment.setDescription(departmentRequest.getDescription());
        existingDepartment.setUpdatedAt(CommonUtils.getCurrentTime());
        existingDepartment.setUpdatedBy("Dev_Department");
        departmentRepository.save(existingDepartment);
    }

    public void deleteDepartmentById(UUID id) {
        departmentRepository.deleteById(id);
    }

    public void deleteAllDepartment() {
        departmentRepository.deleteAll();
    }

    public LinkedHashMap<String, Object> setDepartmentResponse(Department department) {
        LinkedHashMap<String, Object> setDepartmentResp = new LinkedHashMap<>();
        setDepartmentResp.put("id", department.getId());
        setDepartmentResp.put("name", department.getName());
        setDepartmentResp.put("description", department.getDescription());
        return setDepartmentResp;
    }
}
