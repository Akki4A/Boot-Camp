package com.incs83.hrm.business;

import com.incs83.hrm.common.DepartmentRequest;
import com.incs83.hrm.entities.Department;
import com.incs83.hrm.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public void createDepartment(DepartmentRequest departmentRequest){
        Department department = new Department();
        department.setName(departmentRequest.getName());
        department.setDescription(departmentRequest.getDescription());
        departmentRepository.save(department);
    }
    public List<Department> getAllDepartment(){
        return departmentRepository.findAll();
    }

    public Department getDepartmentById(UUID id){
        return departmentRepository.findById(id).orElse(null);
    }

    public void updateDepartment(DepartmentRequest departmentRequest, UUID id){
        Department existingDepartment = departmentRepository.findById(id).orElse(null);
        if (existingDepartment != null) {
            existingDepartment.setName(departmentRequest.getName());
            existingDepartment.setDescription(departmentRequest.getDescription());
            departmentRepository.save(existingDepartment);
        }
    }
    public void deleteDepartmentById(UUID id){
        departmentRepository.deleteById(id);
    }
}
