package com.rkasibhatla.attendanceservice.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Student extends Person {

    @ManyToOne
    private Standard standard;

    public Standard getStandard() {
        return standard;
    }

    public void setStandard(Standard standard) {
        this.standard = standard;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                "standard=" + standard +
                '}';
    }
}
