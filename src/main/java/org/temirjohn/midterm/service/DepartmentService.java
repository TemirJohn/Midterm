package org.temirjohn.midterm.service;

import org.temirjohn.midterm.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getDepartments();
    List<DepartmentDto> searchDepartments(String name);
    DepartmentDto getDepartmentByID(Long id);
    DepartmentDto createDepartment(DepartmentDto departmentDto);
    DepartmentDto updateDepartment(DepartmentDto departmentDto, Long id);
    void deleteDepartment(Long id);
}
