package org.temirjohn.midterm.mapper;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.mapper.DepartmentMapper;
import org.temirjohn.midterm.entity.Department;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    void convertEntityToDtoTest() {
        Department department = new Department();
        department.setId(10L);
        department.setName("IT");

        DepartmentDto departmentDto = departmentMapper.toDto(department);

        Assertions.assertNotNull(departmentDto);
        Assertions.assertNotNull(departmentDto.getId());
        Assertions.assertNotNull(departmentDto.getNameDto());

        Assertions.assertEquals(department.getId(), departmentDto.getId());
        Assertions.assertEquals(department.getName(), departmentDto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(10L);
        departmentDto.setNameDto("IT");

        Department department = departmentMapper.toEntity(departmentDto);

        Assertions.assertNotNull(department);
        Assertions.assertNotNull(department.getId());
        Assertions.assertNotNull(department.getName());

        Assertions.assertEquals(departmentDto.getId(), department.getId());
        Assertions.assertEquals(departmentDto.getNameDto(), department.getName());
    }

    @Test
    void convertListOfDepartmentsToDtoList() {
        Department department1 = new Department();
        department1.setId(10L);
        department1.setName("IT");

        Department department2 = new Department();
        department2.setId(20L);
        department2.setName("Business");

        Department department3 = new Department();
        department3.setId(30L);
        department3.setName("Engineering");

        List<Department> departmentList = new ArrayList<>();
        departmentList.add(department1);
        departmentList.add(department2);
        departmentList.add(department3);

        List<DepartmentDto> departmentDtoList = departmentMapper.toDtoList(departmentList);

        Assertions.assertNotNull(departmentDtoList);
        Assertions.assertNotEquals(0, departmentDtoList.size());
        Assertions.assertEquals(departmentList.size(), departmentDtoList.size());

        for (int i = 0; i < departmentList.size(); i++) {
            Department departmentEntity = departmentList.get(i);
            DepartmentDto departmentDto = departmentDtoList.get(i);

            Assertions.assertNotNull(departmentDto);
            Assertions.assertEquals(departmentEntity.getId(), departmentDto.getId());
            Assertions.assertEquals(departmentEntity.getName(), departmentDto.getNameDto());
        }
    }
}
