package com.rkasibhatla.attendanceservice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StandardDto {
    private Integer id;
    private String name;
    private TeacherDto classTeacher;
    private int strength;

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

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

    public TeacherDto getClassTeacher() {
        return classTeacher;
    }

    public void setClassTeacher(TeacherDto classTeacher) {
        this.classTeacher = classTeacher;
    }
}
