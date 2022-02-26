package com.rkasibhatla.attendanceservice.repository;

import com.rkasibhatla.attendanceservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
}
