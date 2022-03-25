package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.StandardDto;
import com.rkasibhatla.attendanceservice.dto.SubjectDto;
import com.rkasibhatla.attendanceservice.entity.Standard;
import com.rkasibhatla.attendanceservice.entity.Subject;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.mapper.DtoToEntityMapper;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.StandardRepository;
import com.rkasibhatla.attendanceservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private StandardRepository standardRepository;

    public List<SubjectDto> getAllSubjects() {
        List<SubjectDto> subjectDtos = new ArrayList<>();
        List<Subject> subjects = subjectRepository.findAll();
        subjects.forEach(subject -> subjectDtos.add(entityToDtoMapper.getSubjectDtoForSubject(subject)));
        return subjectDtos;
    }

    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subject = dtoToEntityMapper.getSubjectForSubjectDto(subjectDto);
        subject = subjectRepository.save(subject);
        return entityToDtoMapper.getSubjectDtoForSubject(subject);
    }

    public Subject getSubjectByName(String name) {
        return subjectRepository.findSubjectByName(name);
    }

    public void addSubjectToStandard(Integer id, StandardDto standardDto) throws DataNotFoundException {
        Subject subject = subjectRepository.getById(id);
        if(subject == null) {
            throw new DataNotFoundException("Subject with id: " + id + " not found");
        }
        Standard standard = standardRepository.getById(standardDto.getId());
        if(standard == null) {
            throw new DataNotFoundException("Standard with id: " + id + " not found");
        }
        subject.setStandard(standard);
        subjectRepository.save(subject);
    }

    public SubjectDto getOneSubject(Integer id) throws DataNotFoundException {
        Subject subject = subjectRepository.getById(id);
        if(subject == null) {
            throw new DataNotFoundException("Subject with id: " + id + " not found");
        }
        return entityToDtoMapper.getSubjectDtoForSubject(subject);
    }
}
