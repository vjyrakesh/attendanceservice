package com.rkasibhatla.attendanceservice.mapper;

import com.rkasibhatla.attendanceservice.dto.*;
import com.rkasibhatla.attendanceservice.entity.*;
import org.springframework.stereotype.Component;

@Component
public class DtoToEntityMapper {
    public Person getPersonForPersonDto(PersonDto personDto) {
        Person person = new Person();
        person.setId(personDto.getId());
        person.setRole("USER");
        person.setUsername(personDto.getUsername());
        person.setPassword(personDto.getPassword());
        person.setFirstName(personDto.getFirstName());
        person.setLastName(personDto.getLastName());
        return person;
    }

    public Student getStudentForStudentDto(StudentDto studentDto) {
        Student student = new Student();
        student.setId(studentDto.getId());
        student.setUsername(studentDto.getUsername());
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setRole("USER");
        return student;
    }

    public Teacher getTeacherForTeacherDto(TeacherDto teacherDto) {
        Teacher teacher = new Teacher();
        teacher.setId(teacherDto.getId());
        teacher.setUsername(teacherDto.getUsername());
        teacher.setFirstName(teacherDto.getFirstName());
        teacher.setLastName(teacherDto.getLastName());
        teacher.setRole("USER");
        return teacher;
    }

    public Standard getStandardForStandardDto(StandardDto standardDto) {
        Standard standard = new Standard();
        standard.setId(standardDto.getId());
        standard.setName(standardDto.getName());
        return standard;
    }

    public Subject getSubjectForSubjectDto(SubjectDto subjectDto) {
        Subject subject = new Subject();
        subject.setId(subjectDto.getId());
        subject.setName(subjectDto.getName());
        return subject;
    }
}
