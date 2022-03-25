package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.SubjectDto;
import com.rkasibhatla.attendanceservice.entity.Subject;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/subjects")
@CrossOrigin(origins = "http://localhost:3000")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping
    public List<SubjectDto> getAllSubjects() {
        return subjectService.getAllSubjects();
    }

    @PostMapping
    public ResponseEntity<?> addSubject(@RequestBody SubjectDto subject) {
        return new ResponseEntity<>(subjectService.addSubject(subject), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}/standard")
    public ResponseEntity<?> addSubjectToStandard(@PathVariable Integer id, @RequestBody StandardDto standardDto) {
        try {
            subjectService.addSubjectToStandard(id, standardDto);
            return ResponseEntity.ok().build();
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getOneSubject(@PathVariable Integer id) {
        try {
            SubjectDto subjectDto = subjectService.getOneSubject(id);
            return ResponseEntity.ok(subjectDto);
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
