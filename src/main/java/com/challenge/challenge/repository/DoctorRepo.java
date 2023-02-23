package com.challenge.challenge.repository;

import com.challenge.challenge.models.Doctor;
import com.challenge.challenge.models.Pathology;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepo extends CrudRepository<Doctor, Long> {
    Optional<Doctor> findByName(String name);
}
