package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.StudentDto;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/students")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PutMapping(value = "/{id}/standard")
    public ResponseEntity<?> addStudentToStandard(@PathVariable Integer id, @RequestBody StandardDto standardDto) {
        try {
            studentService.addStudentToStandard(id, standardDto);
            return ResponseEntity.ok().build();
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<StudentDto> getAllStudents() {
        return studentService.getAllStudents();
    }
}
