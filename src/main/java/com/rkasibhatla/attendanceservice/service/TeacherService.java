package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.SubjectDto;
import com.rkasibhatla.attendanceservice.dto.TeacherDto;
import com.rkasibhatla.attendanceservice.entity.Subject;
import com.rkasibhatla.attendanceservice.entity.Teacher;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.PersonRespository;
import com.rkasibhatla.attendanceservice.repository.TeacherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class TeacherService {

    private static final Logger logger = LoggerFactory.getLogger(TeacherService.class.getName());
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private PersonRespository personRespository;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public List<TeacherDto> getAllTeachers() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<TeacherDto> result = new ArrayList<>();
        teachers.forEach(teacher -> result.add(entityToDtoMapper.getTeacherDtoForTeacher(teacher)));
        return result;
    }

    public void addSubjectToTeacher(Integer id, SubjectDto subjectDto) throws DataNotFoundException {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher == null) {
            throw new DataNotFoundException("Teacher entity with id: " + id + " not found");
        }
        logger.info("Found teacher: " + teacher.getId());
        Subject subject = subjectService.getSubjectByName(subjectDto.getName());
        if(subject == null) {
            throw new DataNotFoundException("Subject entity with name: " + subjectDto.getName() + " not found");
        }
        logger.info("Found subject: " + subject.getId());
        subject.setTeacher(teacher);
        teacher.getSubjects().add(subject);
        teacher = teacherRepository.save(teacher);
    }

    public List<SubjectDto> getSubjectsOfTeacher(Integer id) throws DataNotFoundException {
        Teacher teacher = teacherRepository.findTeacherById(id);
        if(teacher == null) {
            throw new DataNotFoundException("Teacher entity with id: " + id + " not found");
        }
        Set<Subject> subjects = teacher.getSubjects();
        logger.info("Subjects of teacher: " + subjects);
        List<SubjectDto> result = new ArrayList<>();
        subjects.forEach(subject -> result.add(entityToDtoMapper.getSubjectDtoForSubject(subject)));
        return result;
    }

    public Teacher getTeacherByUsername(String username) {
        return teacherRepository.findTeacherByUsername(username);
    }

    public Teacher getTeacherById(Integer id) {
        return teacherRepository.findTeacherById(id);
    }

    public TeacherDto getTeacherDto(Integer id) throws DataNotFoundException {
        Teacher teacher = getTeacherById(id);
        if(teacher == null) {
            throw new DataNotFoundException("Teacher with id: " + id + " not found");
        }
        return entityToDtoMapper.getTeacherDtoForTeacher(teacher);
    }
}
