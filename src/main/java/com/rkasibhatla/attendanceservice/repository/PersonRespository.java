package com.rkasibhatla.attendanceservice.repository;

import com.rkasibhatla.attendanceservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRespository extends JpaRepository<Person, Integer> {
}
