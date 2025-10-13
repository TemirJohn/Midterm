package org.temirjohn.midterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temirjohn.midterm.entity.Department;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
