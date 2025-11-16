package org.temirjohn.midterm.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "student_teachers",
            joinColumns = @JoinColumn(name = "student_id"), // колонка в join-таблице, ссылающаяся на Student
            inverseJoinColumns = @JoinColumn(name = "teachers_id") // колонка, ссылающаяся на Teacher
    )
    private List<Teacher> teachers;
}