package org.temirjohn.midterm.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.entity.Student;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeacherMapper.class, DepartmentMapper.class})
public interface StudentMapper {
    @Mapping(target = "nameDto", source = "name")
    @Mapping(target = "teacherDto", source = "teachers")
    @Mapping(target = "departmentDto", source = "department")
    StudentDto toDto(Student student);
    @Mapping(target = "name", source = "nameDto")
    @Mapping(target = "teachers", source = "teacherDto")
    @Mapping(target = "department", source = "departmentDto")
    Student toEntity(StudentDto studentDto);

    List<StudentDto> toDtoList(List<Student> entities);
}
