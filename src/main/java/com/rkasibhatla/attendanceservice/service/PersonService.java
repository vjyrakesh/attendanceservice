package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.PersonDto;
import com.rkasibhatla.attendanceservice.entity.Person;
import com.rkasibhatla.attendanceservice.entity.Student;
import com.rkasibhatla.attendanceservice.entity.Teacher;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.PersonRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRespository personRespository;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    private PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public PersonDto registerPerson(PersonDto personDto) {
        Person person = null;
        switch (personDto.getPersonType()) {
            case STUDENT:
                person = new Student();
                break;
            case TEACHER:
                person = new Teacher();
                break;
        }
        person.setUsername(personDto.getUsername());
        person.setPassword(passwordEncoder.encode(personDto.getPassword()));
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        person.setEnabled(true);
        person.setRole("USER");
        person = personRespository.save(person);
        return entityToDtoMapper.getPersonDtoForPerson(person);
    }
}
