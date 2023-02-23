package com.challenge.challenge.repository;

import com.challenge.challenge.models.Consultation;
import com.challenge.challenge.models.Doctor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ConsultationRepo extends CrudRepository<Consultation, Long> {
    Optional<Consultation> findById(Long id);

    List<Consultation> findByPatientId(Long patientId);
/*
This query is using a subquery to count the number of unique patients per specialty.
The subquery groups consultations by their specialty and counts the number of unique patients for each specialty.
It is then joined with the Specialty table so that we can select the specialties that have more than 2 unique patients.
 */
@Query("SELECT s.name, COUNT(DISTINCT c.patient.id) as numPatients " +             //Select the id and name columns from the Specialty table.
        "FROM Consultation c " +
        "JOIN c.specialty s " +                                                    //Join the subquery that groups consultations by specialty and counts the number of unique patients per specialty. The subquery returns two columns: specialty_id and num_patients.
        "GROUP BY s.name " +
        "HAVING COUNT(DISTINCT c.patient.id) > 2")                                 //Filter the results of the subquery to only include specialties with more than 2 unique patients. This subquery is aliased as counts.

    List<Object[]> findSpecialtiesWithMoreThan2Patients();

}
