package org.temirjohn.midterm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.temirjohn.midterm.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
