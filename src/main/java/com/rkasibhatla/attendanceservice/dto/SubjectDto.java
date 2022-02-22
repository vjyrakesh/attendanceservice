package com.rkasibhatla.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SubjectDto {
    private Integer id;
    private String name;
    private TeacherDto teacher;
    private StandardDto standard;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TeacherDto getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherDto teacher) {
        this.teacher = teacher;
    }

    public StandardDto getStandard() {
        return standard;
    }

    public void setStandard(StandardDto standard) {
        this.standard = standard;
    }
}
