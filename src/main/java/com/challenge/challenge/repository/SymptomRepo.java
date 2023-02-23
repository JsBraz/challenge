package com.challenge.challenge.repository;

import com.challenge.challenge.models.Patient;
import com.challenge.challenge.models.Symptom;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SymptomRepo extends CrudRepository<Symptom, Long> {
    Optional<Symptom> findByName(String name);
}
