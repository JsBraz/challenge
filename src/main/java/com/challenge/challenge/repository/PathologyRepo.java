package com.challenge.challenge.repository;

import com.challenge.challenge.models.Pathology;
import com.challenge.challenge.models.Patient;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PathologyRepo extends CrudRepository<Pathology, Long> {
    Optional<Pathology> findByName(String name);
}
