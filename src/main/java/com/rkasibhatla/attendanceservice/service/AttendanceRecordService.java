package com.rkasibhatla.attendanceservice.service;

import com.rkasibhatla.attendanceservice.dto.AttendanceRecordDto;
import com.rkasibhatla.attendanceservice.entity.AttendanceRecord;
import com.rkasibhatla.attendanceservice.entity.Standard;
import com.rkasibhatla.attendanceservice.entity.Student;
import com.rkasibhatla.attendanceservice.entity.Subject;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.mapper.DtoToEntityMapper;
import com.rkasibhatla.attendanceservice.mapper.EntityToDtoMapper;
import com.rkasibhatla.attendanceservice.repository.AttendanceRecordRepository;
import com.rkasibhatla.attendanceservice.repository.StandardRepository;
import com.rkasibhatla.attendanceservice.repository.StudentRepository;
import com.rkasibhatla.attendanceservice.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AttendanceRecordService {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private EntityToDtoMapper entityToDtoMapper;

    @Autowired
    private StandardRepository standardRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private DtoToEntityMapper dtoToEntityMapper;

    public List<AttendanceRecordDto> getAllAttendanceRecords() {
        List<AttendanceRecord> attendanceRecords = attendanceRecordRepository.findAll();
        List<AttendanceRecordDto> attendanceRecordDtos = new ArrayList<>();
        attendanceRecords.forEach(attendanceRecord ->
                attendanceRecordDtos.add(entityToDtoMapper.getAttendanceRecordDtoForAttendanceRecord(attendanceRecord)));
        return attendanceRecordDtos;
    }

    public void addAttendanceRecord(AttendanceRecordDto attendanceRecordDto) throws DataNotFoundException, ParseException {
        Standard standard = standardRepository.getById(attendanceRecordDto.getStandardId());
        if(standard == null) {
            throw new DataNotFoundException("Standard with id: " + attendanceRecordDto.getStandardId() + " not found");
        }
        Subject subject = subjectRepository.getById(attendanceRecordDto.getSubjectId());
        if(subject == null) {
            throw new DataNotFoundException("Subject with id: " + attendanceRecordDto.getSubjectId() + " not found");
        }
        Student student = studentRepository.getById(attendanceRecordDto.getStudentId());
        if(student == null) {
            throw new DataNotFoundException("Student with id: " + attendanceRecordDto.getStudentId() + " not found");
        }
        AttendanceRecord attendanceRecord = dtoToEntityMapper.getAttendanceRecordForAttendanceRecordDto(attendanceRecordDto);
        attendanceRecordRepository.save(attendanceRecord);
    }
}
