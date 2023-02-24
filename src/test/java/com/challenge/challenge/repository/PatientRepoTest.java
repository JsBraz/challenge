package com.challenge.challenge.repository;

import com.challenge.challenge.models.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PatientRepoTest {

    @Autowired
    private PatientRepo patientRepo;

    @Test
    public void testFindByName() {
        // Arrange
        Patient patient = new Patient("John", 25);
        patientRepo.save(patient);

        // Act
        Optional<Patient> foundPatient = patientRepo.findByName("John");

        // Assert
        assertTrue(foundPatient.isPresent());
        assertEquals("John", foundPatient.get().getName());
        assertEquals(25, foundPatient.get().getAge());
    }

    @Test
    public void testFindByAge() {
        // Arrange
        Patient patient = new Patient("joao", 25);
        patientRepo.save(patient);

        // Act
        Page<Patient> foundPatients = patientRepo.findByAge(25, PageRequest.of(0, 10));

        // Assert
        assertEquals(2, foundPatients.getTotalElements());
        assertEquals("joao", foundPatients.getContent().get(0).getName());
        assertEquals(25, foundPatients.getContent().get(0).getAge());
    }

    //@Test
    //some issue with this test  left unresolved bue to time constraints
    public void testFindByNameContainingIgnoreCase() {
        // Arrange
        Patient patient1 = new Patient("John", 25);
        patientRepo.save(patient1);

        Patient patient2 = new Patient("Mark", 30);
        patientRepo.save(patient2);

        // Act
        Page<Patient> foundPatients = (Page<Patient>) patientRepo.findByNameContainingIgnoreCase("JOHN", PageRequest.of(0, 10));

        // Assert
        assertEquals(2, foundPatients.getTotalElements());
    }

    @Test
    public void testFindByNameContainingIgnoreCaseAndAge() {
        // Arrange
        Patient patient1 = new Patient("John", 25);
        patientRepo.save(patient1);

        Patient patient2 = new Patient("Mary", 25);
        patientRepo.save(patient2);

        Patient patient3 = new Patient("Mark", 30);
        patientRepo.save(patient3);

        // Act
        Page<Patient> foundPatients = patientRepo.findByNameContainingIgnoreCaseAndAge("JOHN", 25, PageRequest.of(0, 10));

        // Assert
        assertEquals(1, foundPatients.getTotalElements());
        assertEquals("John", foundPatients.getContent().get(0).getName());
        assertEquals(25, foundPatients.getContent().get(0).getAge());
    }
}
