package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.PersonDto;
import com.rkasibhatla.attendanceservice.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping(value = "/register")
    public ResponseEntity<?> registerPerson(@RequestBody PersonDto personDto) {
        return new ResponseEntity<>(personService.registerPerson(personDto), HttpStatus.CREATED);
    }
}
