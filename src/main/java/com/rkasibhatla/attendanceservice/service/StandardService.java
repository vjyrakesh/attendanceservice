package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.TeacherDto;
import com.rkasibhatla.attendanceservice.entity.Standard;
import com.rkasibhatla.attendanceservice.entity.Teacher;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.mapper.DtoToEntityMapper;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.StandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StandardService {

    @Autowired
    private StandardRepository standardRepository;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public StandardDto addStandard(StandardDto standardDto) {
        Standard standard = dtoToEntityMapper.getStandardForStandardDto(standardDto);
        return entityToDtoMapper.getStandardDtoForStandard(standard);
    }

    public void addClassTeacherToStandard(Integer id, TeacherDto teacherDto) throws DataNotFoundException {
        Standard standard = standardRepository.getById(id);
        if(standard == null) {
            throw new DataNotFoundException("Standard with id: " + id + " not found");
        }
        Teacher teacher = teacherService.getTeacherById(teacherDto.getId());
        if(teacher == null) {
            throw new DataNotFoundException("Teacher with id: " + teacherDto.getId() + " not found");
        }
        standard.setClassTeacher(teacher);
        standardRepository.save(standard);
    }

    public List<StandardDto> getAllStandards() {
        List<StandardDto> standardDtos = new ArrayList<>();
        List<Standard> standards = standardRepository.findAll();
        standards.forEach(standard -> standardDtos.add(entityToDtoMapper.getStandardDtoForStandard(standard)));
        return standardDtos;
    }
}
