package com.rkasibhatla.attendanceservice.controller;

import com.rkasibhatla.attendanceservice.dto.AttendanceRecordDto;
import com.rkasibhatla.attendanceservice.exception.DataNotFoundException;
import com.rkasibhatla.attendanceservice.service.AttendanceRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping(value = "/attendance")
public class AttendanceRecordController {

    @Autowired
    private AttendanceRecordService attendanceRecordService;

    @GetMapping
    public List<AttendanceRecordDto> getAllAttendanceRecords() {
        return attendanceRecordService.getAllAttendanceRecords();
    }

    @PostMapping
    public ResponseEntity<?> addAttendanceRecord(@RequestBody AttendanceRecordDto attendanceRecordDto) {
        try {
            attendanceRecordService.addAttendanceRecord(attendanceRecordDto);
            return new ResponseEntity<>("Attendance added", HttpStatus.CREATED);
        } catch (DataNotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        } catch (ParseException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
