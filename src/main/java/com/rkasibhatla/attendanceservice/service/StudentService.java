package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.StudentDto;
import com.rkasibhatla.attendanceservice.entity.Standard;
import com.rkasibhatla.attendanceservice.entity.Student;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StandardService standardService;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public void addStudentToStandard(Integer id, StandardDto standardDto) throws DataNotFoundException {
        Student student = studentRepository.getById(id);
        if(student == null) {
            throw new DataNotFoundException("Student with id: " + id + " not found");
        }
        Standard standard = null;
        if(standardDto.getId() != null && standardDto.getId() > 0)
            standard = standardService.getStandardById(standardDto.getId());
        else if(standardDto.getName() != null && !standardDto.getName().equals(""))
            standard = standardService.getStandardByName(standardDto.getName());
        if(standard == null) {
            throw new DataNotFoundException("Standard with id: " + standardDto.getId() + " not found");
        }
        student.setStandard(standard);
        studentRepository.save(student);
    }

    public List<StudentDto> getAllStudents() {
        List<StudentDto> studentDtos = new ArrayList<>();
        List<Student> students = studentRepository.findAll();
        students.forEach(student -> studentDtos.add(entityToDtoMapper.getStudentDtoForStudent(student)));
        return studentDtos;
    }

    public StudentDto getOneStudent(Integer id) throws DataNotFoundException {
        Student student = studentRepository.getById(id);
        if(student == null) {
            throw new DataNotFoundException("Student with id: " + id + " not found");
        }
        return entityToDtoMapper.getStudentDtoForStudent(student);
    }

}
