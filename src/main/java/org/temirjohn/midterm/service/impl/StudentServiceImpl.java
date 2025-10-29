package org.temirjohn.midterm.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.mapper.StudentMapper;
import org.temirjohn.midterm.entity.Student;
import org.temirjohn.midterm.repository.StudentRepository;
import org.temirjohn.midterm.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;


    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDto> getStudents() {
        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public List<StudentDto> searchStudents(String name, String teacherName, String departmentName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> studentRoot = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(cb.like(studentRoot.get("name"), "%" + name + "%"));
        }

        if (teacherName != null && !teacherName.isEmpty()) {
            Join<Object, Object> teacherJoin = studentRoot.join("teachers", JoinType.LEFT);
            predicates.add(cb.like(teacherJoin.get("name"), "%" + teacherName + "%"));
        }

        if (departmentName != null && !departmentName.isEmpty()) {
            Join<Object, Object> departmentJoin = studentRoot.join("department", JoinType.LEFT);
            predicates.add(cb.like(departmentJoin.get("name"), "%" + departmentName + "%"));
        }

        query.select(studentRoot).distinct(true).where(cb.and(predicates.toArray(new Predicate[0])));
        List<Student> students = entityManager.createQuery(query).getResultList();
        return studentMapper.toDtoList(students);
    }


    @Override
    public StudentDto getStudentByID(Long id) {
        return studentMapper.toDto(studentRepository.findById(id).orElse(null));
    }

    @Override
    public boolean createStudent(StudentDto studentDto) {
       if (Objects.isNull(studentDto)) {
           return false;
       }
       studentRepository.save(studentMapper.toEntity(studentDto));
       return true;
    }

    @Override
    public boolean updateStudent(StudentDto studentDto, Long id) {
        Student existingStudent = studentRepository.findById(id).orElse(null);
        if (Objects.isNull(existingStudent) || Objects.isNull(studentDto)) {
            return false;
        }
        Student updatedStudent = studentMapper.toEntity(studentDto);
        updatedStudent.setId(id);
        studentRepository.save(updatedStudent);
        return true;
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
