package org.temirjohn.midterm.service;

import org.temirjohn.midterm.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();
    TeacherDto getTeacherByID(Long id);
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto updateTeacher(TeacherDto teacherDto, Long id);
    void deleteTeacher(Long id);
}
