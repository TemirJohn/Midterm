package org.temirjohn.midterm.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.dto.mapper.TeacherMapper;
import org.temirjohn.midterm.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;


    @Test
    void convertEntityToDtoTest() {
        Teacher teacher = new Teacher();
        teacher.setId(5L);
        teacher.setName("Mr. John");

        TeacherDto teacherDto = teacherMapper.toDto(teacher);

        Assertions.assertNotNull(teacherDto);
        Assertions.assertNotNull(teacherDto.getId());
        Assertions.assertNotNull(teacherDto.getNameDto());

        Assertions.assertEquals(teacher.getId(), teacherDto.getId());
        Assertions.assertEquals(teacher.getName(), teacherDto.getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(5L);
        teacherDto.setNameDto("Mr. John");

        Teacher teacher = teacherMapper.toEntity(teacherDto);

        Assertions.assertNotNull(teacher);
        Assertions.assertNotNull(teacher.getId());
        Assertions.assertNotNull(teacher.getName());

        Assertions.assertEquals(teacherDto.getId(), teacher.getId());
        Assertions.assertEquals(teacherDto.getNameDto(), teacher.getName());
    }

    @Test
    void convertListOfTeachersToDtoList() {
        Teacher teacher1 = new Teacher();
        teacher1.setId(5L);
        teacher1.setName("Mr. John");

        Teacher teacher2 = new Teacher();
        teacher2.setId(6L);
        teacher2.setName("Ms. Smith");

        Teacher teacher3 = new Teacher();
        teacher3.setId(7L);
        teacher3.setName("Dr. Brown");

        List<Teacher> teacherList = new ArrayList<>();
        teacherList.add(teacher1);
        teacherList.add(teacher2);
        teacherList.add(teacher3);

        List<TeacherDto> teacherDtoList = teacherMapper.toDtoList(teacherList);

        Assertions.assertNotNull(teacherDtoList);
        Assertions.assertNotEquals(0, teacherDtoList.size());
        Assertions.assertEquals(teacherList.size(), teacherDtoList.size());

        for (int i = 0; i < teacherList.size(); i++) {
            Teacher teacherEntity = teacherList.get(i);
            TeacherDto teacherDto = teacherDtoList.get(i);

            Assertions.assertNotNull(teacherDto);
            Assertions.assertEquals(teacherEntity.getId(), teacherDto.getId());
            Assertions.assertEquals(teacherEntity.getName(), teacherDto.getNameDto());
        }
    }
}
