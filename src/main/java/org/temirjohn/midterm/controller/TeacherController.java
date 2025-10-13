package org.temirjohn.midterm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.temirjohn.midterm.dto.TeacherDto;
import org.temirjohn.midterm.service.TeacherService;

@Controller
@RequestMapping("/teachers")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public ResponseEntity<?> getTeachers() {
        return new ResponseEntity<>(teacherService.getTeachers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTeacherByID(@PathVariable Long id) {
        return new ResponseEntity<>(teacherService.getTeacherByID(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@RequestBody TeacherDto teacherDto) {
        return new ResponseEntity<>(teacherService.createTeacher(teacherDto), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(@PathVariable Long id, @RequestBody TeacherDto teacherDto) {
        return new ResponseEntity<>(teacherService.updateTeacher(teacherDto, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable Long id) {
        try {
            teacherService.deleteTeacher(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
