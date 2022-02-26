package com.rkasibhatla.attendanceservice.repository;

import com.rkasibhatla.attendanceservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
    public Subject findSubjectByName(String name);
}
