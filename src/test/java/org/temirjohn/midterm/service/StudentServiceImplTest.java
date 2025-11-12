package org.temirjohn.midterm.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.temirjohn.midterm.dto.DepartmentDto;
import org.temirjohn.midterm.dto.StudentDto;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.service.impl.StudentServiceImpl;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class StudentServiceImplTest {
    @Autowired
    private StudentServiceImpl studentService;


    @Test
    void getStudents() {
        List<StudentDto> studentDtos = studentService.getStudents();

        Assertions.assertNotNull(studentDtos);
        Assertions.assertNotEquals(0, studentDtos.size());
        for (int i = 0; i < studentDtos.size(); i++) {
            StudentDto dto = studentDtos.get(i);

            Assertions.assertNotNull(dto);
            Assertions.assertNotNull(dto.getId());
            Assertions.assertNotNull(dto.getNameDto());
            Assertions.assertNotNull(dto.getDepartmentDto());
            Assertions.assertNotNull(dto.getTeacherDto());
        }
    }

    @Test
    void getStudentByID() {
        Random random = new Random();

        int randomIndex = random.nextInt(studentService.getStudents().size());

        Long someIndex = studentService.getStudents().get(randomIndex).getId();

        StudentDto studentDto = studentService.getStudentByID(someIndex);

        Assertions.assertNotNull(studentDto);
        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getNameDto());
        Assertions.assertNotNull(studentDto.getDepartmentDto());
        Assertions.assertNotNull(studentDto.getTeacherDto());

        StudentDto check = studentService.getStudentByID(-1L);
        Assertions.assertNull(check);
    }

    @Test
    void createStudent() {
        DepartmentDto departmentdto = new DepartmentDto();
        departmentdto.setNameDto("IT");

        TeacherDto teacherdto = new TeacherDto();
        teacherdto.setNameDto("Mr. John");


        StudentDto studentdto = new StudentDto();
        studentdto.setId(1L);
        studentdto.setNameDto("Temir");
        studentdto.setDepartmentDto(departmentdto);
        studentdto.setTeacherDto(List.of(teacherdto));

        StudentDto created = studentService.createStudent(studentdto);

        Assertions.assertNotNull(created);
        Assertions.assertNotNull(created.getId());
        Assertions.assertNotNull(created.getNameDto());
        Assertions.assertNotNull(created.getDepartmentDto());
        Assertions.assertNotNull(created.getTeacherDto());

        StudentDto fetched = studentService.getStudentByID(created.getId());

        Assertions.assertNotNull(fetched);
        Assertions.assertNotNull(fetched.getId());
        Assertions.assertNotNull(fetched.getNameDto());
        Assertions.assertNotNull(fetched.getDepartmentDto());
        Assertions.assertNotNull(fetched.getTeacherDto());

        Assertions.assertEquals(created.getId(), fetched.getId());
        Assertions.assertEquals(created.getNameDto(), fetched.getNameDto());
        Assertions.assertEquals(created.getDepartmentDto().getNameDto(), fetched.getDepartmentDto().getNameDto());
        Assertions.assertEquals(created.getTeacherDto().size(), fetched.getTeacherDto().size());
        Assertions.assertEquals(created.getTeacherDto().get(0).getNameDto(), fetched.getTeacherDto().get(0).getNameDto());
    }


    @Test
    void updateStudentTest() {
        Random random = new Random();

        List<StudentDto> students = studentService.getStudents();
        Assertions.assertFalse(students.isEmpty(), "No students found in database");

        int randomIndex = random.nextInt(students.size());
        StudentDto existingStudent = students.get(randomIndex);
        Long studentId = existingStudent.getId();

        StudentDto updateRequest = StudentDto.builder()
                .id(studentId)
                .nameDto("Updated Name")
                .departmentDto(existingStudent.getDepartmentDto())
                .teacherDto(existingStudent.getTeacherDto())
                .build();

        StudentDto updated = studentService.updateStudent(updateRequest, studentId);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(studentId, updated.getId());
        Assertions.assertEquals("Updated Name", updated.getNameDto());

        if (existingStudent.getDepartmentDto() != null) {
            Assertions.assertEquals(
                    existingStudent.getDepartmentDto().getId(),
                    updated.getDepartmentDto().getId()
            );
        }

        if (existingStudent.getTeacherDto() != null && !existingStudent.getTeacherDto().isEmpty()) {
            Assertions.assertEquals(
                    existingStudent.getTeacherDto().get(0).getId(),
                    updated.getTeacherDto().get(0).getId()
            );
        }

        StudentDto found = studentService.getStudentByID(studentId);
        Assertions.assertNotNull(found);
        Assertions.assertEquals("Updated Name", found.getNameDto());
        Assertions.assertEquals(updated.getId(), found.getId());
    }


    @Test
    void deleteStudent() {
        Random random = new Random();
        int randomIndex = random.nextInt(studentService.getStudents().size());
        Long someIndex = studentService.getStudents().get(randomIndex).getId();

        studentService.deleteStudent(someIndex);

        StudentDto studentDto = studentService.getStudentByID(someIndex);

        Assertions.assertNull(studentDto);
    }
}
