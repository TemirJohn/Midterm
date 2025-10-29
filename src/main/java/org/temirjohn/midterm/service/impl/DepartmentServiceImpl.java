package org.temirjohn.midterm.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.mapper.DepartmentMapper;
import org.temirjohn.midterm.entity.Department;
import org.temirjohn.midterm.repository.DepartmentRepository;
import org.temirjohn.midterm.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentDto> getDepartments() {
        return departmentMapper.toDtoList(departmentRepository.findAll());
    }

    @Override
    public List<DepartmentDto> searchDepartments(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Department> query = cb.createQuery(Department.class);
        Root<Department> departmentRoot =query.from(Department.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(departmentRoot.get("name"), "%" + name + "%"));
        }

        query.select(departmentRoot).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<Department> departments = entityManager.createQuery(query).getResultList();
        return departmentMapper.toDtoList(departments);
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
