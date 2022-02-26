package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.TeacherDto;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/standards")
public class StandardController {

    @Autowired
    private StandardService standardService;

    @PostMapping
    public ResponseEntity<?> addStandard(@RequestBody StandardDto standardDto) {
        standardDto = standardService.addStandard(standardDto);
        return new ResponseEntity<>(standardDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/classteacher")
    public ResponseEntity<?> addClassTeacher(@PathVariable Integer id, @RequestBody TeacherDto teacherDto) {
        try {
            standardService.addClassTeacherToStandard(id, teacherDto);
            return ResponseEntity.ok().build();
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<StandardDto> getAllStandards() {
        return standardService.getAllStandards();
    }

}
