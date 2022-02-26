package com.rkasibhatla.attendanceservice.repository;

import com.rkasibhatla.attendanceservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
    Teacher findTeacherById(Integer id);
    Teacher findTeacherByUsername(String username);
}
