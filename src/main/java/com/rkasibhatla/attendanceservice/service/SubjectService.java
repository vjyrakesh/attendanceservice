package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.SubjectDto;
import com.rkasibhatla.attendanceservice.entity.Subject;
import com.rkasibhatla.attendanceservice.mapper.DtoToEntityMapper;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    public List<Subject> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public SubjectDto addSubject(SubjectDto subjectDto) {
        Subject subject = dtoToEntityMapper.getSubjectForSubjectDto(subjectDto);
        subject = subjectRepository.save(subject);
        return entityToDtoMapper.getSubjectDtoForSubject(subject);
    }

    public Subject getSubjectByName(String name) {
        return subjectRepository.findSubjectByName(name);
    }
}
