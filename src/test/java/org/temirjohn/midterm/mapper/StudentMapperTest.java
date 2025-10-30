package org.temirjohn.midterm.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.dto.mapper.StudentMapper;
import org.temirjohn.midterm.entity.Department;
import org.temirjohn.midterm.entity.Student;
import org.temirjohn.midterm.entity.Teacher;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest(){
        Department department = new Department();
        department.setId(10L);
        department.setName("IT");

        Teacher teacher = new Teacher();
        teacher.setId(5L);
        teacher.setName("Mr. John");

        Student student = new Student();
        student.setId(1L);
        student.setName("Temir");
        student.setDepartment(department);
        student.setTeachers(List.of(teacher));

        StudentDto studentDto = studentMapper.toDto(student);

        Assertions.assertNotNull(studentDto);

        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getNameDto());
        Assertions.assertNotNull(studentDto.getDepartmentDto());
        Assertions.assertNotNull(studentDto.getTeacherDto());

        Assertions.assertEquals(student.getId(), studentDto.getId());
        Assertions.assertEquals(student.getName(), studentDto.getNameDto());
        Assertions.assertEquals(student.getDepartment().getId(), studentDto.getDepartmentDto().getId());
        Assertions.assertEquals(student.getDepartment().getName(), studentDto.getDepartmentDto().getNameDto());
        Assertions.assertEquals(teacher.getId(), studentDto.getTeacherDto().get(0).getId());
        Assertions.assertEquals(teacher.getName(), studentDto.getTeacherDto().get(0).getNameDto());
    }

    @Test
    void convertDtoToEntityTest() {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(1L);
        studentDto.setNameDto("Temir");
        studentDto.setDepartmentDto(new DepartmentDto());
        studentDto.setTeacherDto(List.of(new TeacherDto()));

        Student student = studentMapper.toEntity(studentDto);

        Assertions.assertNotNull(student);
        Assertions.assertEquals(studentDto.getId(), student.getId());
        Assertions.assertEquals(studentDto.getNameDto(), student.getName());
        Assertions.assertNotNull(student.getDepartment());
        Assertions.assertNotNull(student.getTeachers());
    }

    @Test
    void convertListOfStudentsToDtoList() {

        Department department = new Department();
        department.setId(10L);
        department.setName("IT");

        Teacher teacher = new Teacher();
        teacher.setId(5L);
        teacher.setName("Mr. John");

        Student student = new Student();
        student.setId(1L);
        student.setName("Temir");
        student.setDepartment(department);
        student.setTeachers(List.of(teacher));

        Student student2 = new Student();
        student2.setId(2L);
        student2.setName("Aruzhan");
        student2.setDepartment(department);
        student2.setTeachers(List.of(teacher));

        List<Student> studentList = new ArrayList<>();
        studentList.add(student);
        studentList.add(student2);

        List<StudentDto> studentDtoList = studentMapper.toDtoList(studentList);
        Assertions.assertNotNull(studentDtoList);

        Assertions.assertNotEquals(0, studentDtoList.size());

        Assertions.assertEquals(studentList.size(), studentDtoList.size());

        for (int i = 0; i < studentList.size(); i++) {
            Student studentEntity = studentList.get(i);
            StudentDto studentDto = studentDtoList.get(i);

            Assertions.assertNotNull(studentDto);

            Assertions.assertEquals(studentEntity.getId(), studentDto.getId());
            Assertions.assertEquals(studentEntity.getName(), studentDto.getNameDto());

            Assertions.assertEquals(studentEntity.getDepartment().getId(), studentDto.getDepartmentDto().getId());
            Assertions.assertEquals(studentEntity.getDepartment().getName(), studentDto.getDepartmentDto().getNameDto());

            Assertions.assertEquals(studentEntity.getTeachers().size(), studentDto.getTeacherDto().size());
            Assertions.assertEquals(studentEntity.getTeachers().get(0).getId(), studentDto.getTeacherDto().get(0).getId());
            Assertions.assertEquals(studentEntity.getTeachers().get(0).getName(), studentDto.getTeacherDto().get(0).getNameDto());
        }
    }
}
