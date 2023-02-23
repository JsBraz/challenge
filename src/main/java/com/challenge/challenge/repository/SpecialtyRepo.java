package com.challenge.challenge.repository;

import com.challenge.challenge.models.Consultation;
import com.challenge.challenge.models.Specialty;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpecialtyRepo extends CrudRepository<Specialty, Long> {
    Optional<Specialty> findById(Long id);
}
