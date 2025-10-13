package org.temirjohn.midterm.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
    private Long id;
    private String nameDto;
    private List<TeacherDto> teacherDto;
    private DepartmentDto departmentDto;
}
