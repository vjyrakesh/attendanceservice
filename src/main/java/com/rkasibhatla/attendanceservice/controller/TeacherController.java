package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.SubjectDto;
import com.rkasibhatla.attendanceservice.dto.TeacherDto;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/teachers")
@CrossOrigin(origins = "http://localhost:3000")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<TeacherDto> getAllTeachers() {
        return teacherService.getAllTeachers();
    }

    @PutMapping(value = "/{id}/subjects")
    public ResponseEntity<?> addSubjectToTeacher(@PathVariable Integer id, @RequestBody SubjectDto subjectDto) {
        try {
            teacherService.addSubjectToTeacher(id, subjectDto);
            return ResponseEntity.ok().build();
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}/subjects")
    public ResponseEntity<?> getSubjectsOfTeacher(@PathVariable Integer id) {
        try {
            List<SubjectDto> subjects = teacherService.getSubjectsOfTeacher(id);
            return new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneTeacher(@PathVariable Integer id) {
        try {
            TeacherDto teacherDto = teacherService.getTeacherDto(id);
            return ResponseEntity.ok(teacherDto);
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
