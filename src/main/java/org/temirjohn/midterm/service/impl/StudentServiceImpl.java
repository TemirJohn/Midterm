package org.temirjohn.midterm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.mapper.StudentMapper;
import org.temirjohn.midterm.entity.Student;
import org.temirjohn.midterm.repository.StudentRepository;
import org.temirjohn.midterm.service.StudentService;

import java.util.List;
import java.util.Objects;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public List<StudentDto> getStudents() {
        return studentMapper.toDtoList(studentRepository.findAll());
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
