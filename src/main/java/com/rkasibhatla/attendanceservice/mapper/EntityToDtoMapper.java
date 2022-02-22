package com.rkasibhatla.attendanceservice.mapper;

import com.rkasibhatla.attendanceservice.dto.*;
import com.rkasibhatla.attendanceservice.entity.*;
import com.rkasibhatla.attendanceservice.enumerator.PersonType;
import org.springframework.stereotype.Component;

@Component
public class EntityToDtoMapper {
    public PersonDto getPersonDtoForPerson(Person person) {
        PersonDto personDto = new PersonDto();
        personDto.setId(person.getId());
        personDto.setUsername(person.getUsername());
        personDto.setFirstName(person.getFirstName());
        personDto.setLastName(person.getLastName());
        return personDto;
    }

    public TeacherDto getTeacherDtoForTeacher(Teacher teacher) {
        TeacherDto teacherDto = new TeacherDto();
        teacherDto.setId(teacher.getId());
        teacherDto.setUsername(teacher.getUsername());
        teacherDto.setFirstName(teacher.getFirstName());
        teacherDto.setLastName(teacher.getLastName());
        teacherDto.setPersonType(PersonType.TEACHER);
        teacherDto.setClassTeacherFor(teacher.getClassTeacherFor().getName());
        return teacherDto;
    }

    public StudentDto getStudentDtoForStudent(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setId(student.getId());
        studentDto.setUsername(student.getUsername());
        studentDto.setFirstName(student.getFirstName());
        studentDto.setLastName(student.getLastName());
        studentDto.setStandard(student.getStandard().getName());
        studentDto.setPersonType(PersonType.STUDENT);
        return studentDto;
    }

    public StandardDto getStandardDtoForStandard(Standard standard) {
        StandardDto standardDto = new StandardDto();
        standardDto.setId(standard.getId());
        standardDto.setName(standard.getName());
        standardDto.setClassTeacher(getTeacherDtoForTeacher(standard.getClassTeacher()));
        return standardDto;
    }

    public SubjectDto getSubjectDtoForSubject(Subject subject) {
        SubjectDto subjectDto = new SubjectDto();
        subjectDto.setId(subject.getId());
        subjectDto.setName(subject.getName());
        subjectDto.setStandard(getStandardDtoForStandard(subject.getStandard()));
        subjectDto.setTeacher(getTeacherDtoForTeacher(subject.getTeacher()));
        return subjectDto;
    }
}
