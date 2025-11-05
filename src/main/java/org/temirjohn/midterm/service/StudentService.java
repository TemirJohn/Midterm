package org.temirjohn.midterm.service;

import org.temirjohn.midterm.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getStudents();
    List<StudentDto> searchStudents(String name, String teacherName, String departmentName);
    StudentDto getStudentByID(Long id);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(StudentDto studentDto, Long id);
    void deleteStudent(Long id);

}
