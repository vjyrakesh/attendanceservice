package com.rkasibhatla.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TeacherDto extends PersonDto {
    private String classTeacherFor;

    public String getClassTeacherFor() {
        return classTeacherFor;
    }

    public void setClassTeacherFor(String classTeacherFor) {
        this.classTeacherFor = classTeacherFor;
    }
}
