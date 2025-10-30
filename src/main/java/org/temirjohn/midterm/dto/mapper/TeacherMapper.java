package org.temirjohn.midterm.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.entity.Teacher;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TeacherMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameDto", source = "name")
    TeacherDto toDto(Teacher teacher);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "nameDto")
    Teacher toEntity(TeacherDto teacherDto);

    List<TeacherDto> toDtoList(List<Teacher> entities);
}
