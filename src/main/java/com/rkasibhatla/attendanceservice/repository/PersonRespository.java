package com.rkasibhatla.attendanceservice.repository;

import com.rkasibhatla.attendanceservice.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRespository extends JpaRepository<Person, Integer> {
}
