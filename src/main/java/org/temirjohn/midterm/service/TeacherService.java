package org.temirjohn.midterm.service;

import org.temirjohn.midterm.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getTeachers();
    TeacherDto getTeacherByID(Long id);
    boolean createTeacher(TeacherDto teacherDto);
    boolean updateTeacher(TeacherDto teacherDto, Long id);
    void deleteTeacher(Long id);
}
