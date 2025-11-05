package org.temirjohn.midterm.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.service.impl.TeacherServiceImpl;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class TeacherServiceImplTest {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Test
    void getTeachers() {
        List<TeacherDto> teacherDtos = teacherService.getTeachers();

        Assertions.assertNotNull(teacherDtos);
        Assertions.assertNotEquals(0, teacherDtos.size());
        for (int i = 0; i < teacherDtos.size(); i++) {
            TeacherDto dto = teacherDtos.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
        }
    }

    @Test
    void getTeacherByID() {
        List<TeacherDto> teachers = teacherService.getTeachers();
        Assertions.assertFalse(teachers.isEmpty());

        Random random = new Random();
        TeacherDto randomTeacher = teachers.get(random.nextInt(teachers.size()));

        TeacherDto found = teacherService.getTeacherByID(randomTeacher.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals(randomTeacher.getId(), found.getId());
        Assertions.assertEquals(randomTeacher.getNameDto(), found.getNameDto());

        TeacherDto notFound = teacherService.getTeacherByID(-1L);
        Assertions.assertNull(notFound);
    }

    @Test
    void createTeacher() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setNameDto("Albert Einstein");

        TeacherDto created = teacherService.createTeacher(teacherDto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertEquals("Albert Einstein", created.getNameDto());

        TeacherDto fetched = teacherService.getTeacherByID(created.getId());
        Assertions.assertNotNull(fetched);
        Assertions.assertEquals(created.getId(), fetched.getId());
        Assertions.assertEquals(created.getNameDto(), fetched.getNameDto());
    }

    @Test
    void updateTeacher() {
        List<TeacherDto> teachers = teacherService.getTeachers();
        Assertions.assertFalse(teachers.isEmpty());

        Random random = new Random();
        TeacherDto existing = teachers.get(random.nextInt(teachers.size()));

        TeacherDto update = TeacherDto.builder()
                .id(existing.getId())
                .nameDto("Updated Teacher")
                .build();

        TeacherDto updated = teacherService.updateTeacher(update, existing.getId());

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(existing.getId(), updated.getId());
        Assertions.assertEquals("Updated Teacher", updated.getNameDto());

        TeacherDto fetched = teacherService.getTeacherByID(existing.getId());
        Assertions.assertEquals("Updated Teacher", fetched.getNameDto());
    }

    @Test
    void deleteTeacher() {
        List<TeacherDto> teachers = teacherService.getTeachers();
        Assertions.assertFalse(teachers.isEmpty());

        Random random = new Random();
        Long id = teachers.get(random.nextInt(teachers.size())).getId();

        teacherService.deleteTeacher(id);

        TeacherDto deleted = teacherService.getTeacherByID(id);
        Assertions.assertNull(deleted);
    }
}
