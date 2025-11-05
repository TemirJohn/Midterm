package org.temirjohn.midterm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.dto.mapper.TeacherMapper;
import org.temirjohn.midterm.entity.Department;
import org.temirjohn.midterm.entity.Teacher;
import org.temirjohn.midterm.repository.TeacherRepository;
import org.temirjohn.midterm.service.TeacherService;

import java.util.List;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public List<TeacherDto> getTeachers() {
        return teacherMapper.toDtoList(teacherRepository.findAll());
    }

    @Override
    public TeacherDto getTeacherByID(Long id) {
        return teacherMapper.toDto(teacherRepository.findById(id).orElse(null));
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        if (Objects.isNull(teacherDto)) {
            return null;
        }
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher savedTeacher= teacherRepository.save(teacher);
        return teacherMapper.toDto(savedTeacher);
    }

    @Override
    public TeacherDto updateTeacher(TeacherDto teacherDto, Long id) {
        Teacher existingTeacher = teacherRepository.findById(id).orElse(null);
        if (Objects.isNull(existingTeacher) || Objects.isNull(teacherDto)) {
            return null;
        }
        existingTeacher.setName(teacherDto.getNameDto());
        Teacher saved = teacherRepository.save(existingTeacher);
        return teacherMapper.toDto(saved);
    }

    @Override
    public void deleteTeacher(Long id) {
        teacherRepository.deleteById(id);
    }
}
