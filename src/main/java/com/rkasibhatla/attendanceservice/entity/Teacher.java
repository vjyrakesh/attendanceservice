package com.rkasibhatla.attendanceservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Teacher extends Person {

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private Set<Subject> subjects = new HashSet<>();

    @OneToOne(mappedBy = "classTeacher", fetch = FetchType.LAZY)
    private Standard classTeacherFor;

    public Set<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<Subject> subjects) {
        this.subjects = subjects;
    }

    public Standard getClassTeacherFor() {
        return classTeacherFor;
    }

    public void setClassTeacherFor(Standard classTeacherFor) {
        this.classTeacherFor = classTeacherFor;
    }
}
