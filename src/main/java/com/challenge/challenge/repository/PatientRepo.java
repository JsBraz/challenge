package com.challenge.challenge.repository;

import com.challenge.challenge.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepo extends CrudRepository<Patient, Long> {
    Optional<Patient> findByName(String name);

    Page<Patient> findByAge(int age, Pageable pageable);

    /*
    Spring Boot uses the naming convention of the method to determine what query to generate.
    In this case, the method names findByNameContainingIgnoreCaseAndAge and findByNameContainingIgnoreCase follow the naming convention of Spring Data JPA repository methods.
    The prefix "find" indicates that this method is used for querying data, and "By" is used to specify the properties or fields to filter by.
    In this case, "NameContainingIgnoreCase" specifies that the name field should be searched for a specific string, ignoring case.
    The "And" operator indicates that both name and age fields should be used in the filter, while leaving it out would mean only one field is used for filtering.
    Spring Data JPA will generate the appropriate SQL query based on the method name and parameters, and execute it against the database.
     */
    List<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Patient> findByNameContainingIgnoreCaseAndAge(String name, int age, Pageable pageable);


}
