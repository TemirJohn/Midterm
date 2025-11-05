package org.temirjohn.midterm.service.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.dto.mapper.DepartmentMapper;
import org.temirjohn.midterm.dto.mapper.StudentMapper;
import org.temirjohn.midterm.dto.mapper.TeacherMapper;
import org.temirjohn.midterm.entity.Department;
import org.temirjohn.midterm.entity.Student;
import org.temirjohn.midterm.entity.Teacher;
import org.temirjohn.midterm.repository.DepartmentRepository;
import org.temirjohn.midterm.repository.StudentRepository;
import org.temirjohn.midterm.repository.TeacherRepository;
import org.temirjohn.midterm.service.StudentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private TeacherRepository teacherRepository;

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
    public StudentDto createStudent(StudentDto studentDto) {
        if (Objects.isNull(studentDto)) {
            return null;
        }
        studentDto.setId(null);
        Student student = studentMapper.toEntity(studentDto);
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }


    @Override
    public StudentDto updateStudent(StudentDto studentDto, Long id) {
        if (studentDto == null) return null;
        Student existing = studentRepository.findById(id).orElse(null);
        if (existing == null) return null;

        // Update name
        existing.setName(studentDto.getNameDto());

        // Update department if provided
        if (studentDto.getDepartmentDto() != null && studentDto.getDepartmentDto().getId() != null) {
            Department department = departmentRepository.findById(studentDto.getDepartmentDto().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Department not found"));
            existing.setDepartment(department);
        }

        // Update teachers if provided
        if (studentDto.getTeacherDto() != null && !studentDto.getTeacherDto().isEmpty()) {
            List<Long> teacherIds = studentDto.getTeacherDto().stream()
                    .map(TeacherDto::getId)
                    .filter(Objects::nonNull)
                    .toList();
            List<Teacher> teachers = teacherRepository.findAllById(teacherIds);
            existing.setTeachers(teachers);
        }

        return studentMapper.toDto(studentRepository.save(existing));
    }


    @Override
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
