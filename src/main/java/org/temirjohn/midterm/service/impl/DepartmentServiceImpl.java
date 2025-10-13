package org.temirjohn.midterm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.mapper.DepartmentMapper;
import org.temirjohn.midterm.entity.Department;
import org.temirjohn.midterm.repository.DepartmentRepository;
import org.temirjohn.midterm.service.DepartmentService;

import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentMapper.toDtoList(departmentRepository.findAll());
    }

    @Override
    public DepartmentDto getDepartmentByID(Long id) {
        return departmentMapper.toDto(departmentRepository.findById(id).orElse(null));
    }

    @Override
    public boolean createDepartment(DepartmentDto departmentDto) {
        if (Objects.isNull(departmentDto)) {
            return false;
        }
        departmentRepository.save(departmentMapper.toEntity(departmentDto));
        return true;
    }

    @Override
    public boolean updateDepartment(DepartmentDto departmentDto, Long id) {
        Department existingDepartment = departmentRepository.findById(id).orElse(null);
        if (Objects.isNull(existingDepartment) || Objects.isNull(departmentDto)) {
            return false;
        }
        existingDepartment.setName(departmentDto.getNameDto());
        departmentRepository.save(existingDepartment);
        return true;
    }

    @Override
    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }
}
