package com.challenge.challenge.services;

import com.challenge.challenge.models.Patient;
import com.challenge.challenge.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepo patientRepo;

    @Autowired
    public PatientService(PatientRepo patientRepo) {
        this.patientRepo = patientRepo;
    }

    public Iterable<Patient> findAll(Pageable pageable) {
        return this.patientRepo.findAll();
    }

    public Optional<Patient> findById(Long id) {
        return this.patientRepo.findById(id);
    }

    public Optional<Patient> createPatient(Patient patient) {
        Optional<Patient> optionalPatient = this.patientRepo.findByName(patient.getName());
        if (optionalPatient.isPresent()) {
            return Optional.empty();
        }
        Patient createdpatient = this.patientRepo.save(patient);
        return Optional.of(createdpatient);
    }

    public Optional<Patient> findByName(String name) {
        return this.patientRepo.findByName(name);
    }

    public void deletePatient(Optional<Patient> patient) {
        patientRepo.delete(patient.get());
    }

    public List<Patient> findByNameContainingIgnoreCase(String name, Pageable pageable) {
        return patientRepo.findByNameContainingIgnoreCase(name, pageable);
    }

    public Iterable<Patient> findByAge(int age, Pageable pageable) {
        return patientRepo.findByAge(age, pageable);
    }

    public Iterable<Patient> findByNameContainingIgnoreCaseAndAge(String name, int age, Pageable pageable) {
        return patientRepo.findByNameContainingIgnoreCaseAndAge(name, age, pageable);
    }

    public Optional<Patient> updatePatient(Patient updatedPatient) {
        Optional<Patient> existingPatient = patientRepo.findById(updatedPatient.getId());
        if (existingPatient.isPresent()) {
            Patient patientToUpdate = existingPatient.get();
            patientToUpdate.setName(updatedPatient.getName());
            patientToUpdate.setAge(updatedPatient.getAge());
            return Optional.of(patientRepo.save(patientToUpdate));
        } else {
            return Optional.empty();
        }
    }
}
