package org.temirjohn.midterm.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.entity.Department;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {
    @Mapping(target = "id", source = "id")
    @Mapping(target = "nameDto", source = "name")
    DepartmentDto toDto(Department department);
    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "nameDto")
    Department toEntity(DepartmentDto departmentDto);

    List<DepartmentDto> toDtoList(List<Department> entities);
}
