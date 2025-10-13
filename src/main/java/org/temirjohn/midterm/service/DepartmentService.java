package org.temirjohn.midterm.service;

import org.temirjohn.midterm.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    List<DepartmentDto> getDepartments();
    DepartmentDto getDepartmentByID(Long id);
    boolean createDepartment(DepartmentDto departmentDto);
    boolean updateDepartment(DepartmentDto departmentDto, Long id);
    void deleteDepartment(Long id);
}
