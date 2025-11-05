package org.temirjohn.midterm.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.StudentDto;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class DepartmentServiceImplTest {
    @Autowired
    private DepartmentService departmentService;


    @Test
    void getDepartments() {
        List<DepartmentDto> departmentDtos = departmentService.getDepartments();

        Assertions.assertNotNull(departmentDtos);
        Assertions.assertNotEquals(0, departmentDtos.size());
        for (int i = 0; i < departmentDtos.size(); i++) {
            DepartmentDto dto = departmentDtos.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
        }
    }

    @Test
    void getDepartmentByID() {
        Random random = new Random();

        int randomIndex = random.nextInt(departmentService.getDepartments().size());

        Long someIndex = departmentService.getDepartments().get(randomIndex).getId();

        DepartmentDto departmentDto = departmentService.getDepartmentByID(someIndex);

        Assertions.assertNotNull(departmentDto);
        Assertions.assertNotNull(departmentDto.getId());
        Assertions.assertNotNull(departmentDto.getNameDto());

        DepartmentDto check = departmentService.getDepartmentByID(-1L);
        Assertions.assertNull(check);
    }

    @Test
    void createDepartment() {
        DepartmentDto departmentdto = new DepartmentDto();
        departmentdto.setNameDto("IT");

        DepartmentDto created = departmentService.createDepartment(departmentdto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());

        DepartmentDto fetched = departmentService.getDepartmentByID(created.getId());
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals(created.getId(), fetched.getId());
    }

    @Test
    void updateDepartment() {
        List<DepartmentDto> deps = departmentService.getDepartments();
        Assertions.assertFalse(deps.isEmpty());

        Random random = new Random();
        DepartmentDto existing = deps.get(random.nextInt(deps.size()));

        DepartmentDto update = DepartmentDto.builder()
                .id(existing.getId())
                .nameDto("Updated Department")
                .build();

        DepartmentDto updated = departmentService.updateDepartment(update, existing.getId());

        Assertions.assertNotNull(updated);
        Assertions.assertEquals("Updated Department", updated.getNameDto());

        DepartmentDto fetched = departmentService.getDepartmentByID(existing.getId());
        Assertions.assertEquals("Updated Department", fetched.getNameDto());
    }

    @Test
    void deleteDepartment() {
        List<DepartmentDto> deps = departmentService.getDepartments();
        Assertions.assertFalse(deps.isEmpty());

        Random random = new Random();
        Long id = deps.get(random.nextInt(deps.size())).getId();

        departmentService.deleteDepartment(id);

        DepartmentDto deleted = departmentService.getDepartmentByID(id);
        Assertions.assertNull(deleted);
    }
}
