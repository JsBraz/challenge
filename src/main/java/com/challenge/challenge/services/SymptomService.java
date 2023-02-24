package com.challenge.challenge.services;

import com.challenge.challenge.models.Symptom;
import com.challenge.challenge.repository.SymptomRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SymptomService {

    private SymptomRepo symptomRepo;

    public SymptomService(SymptomRepo symptomRepo) {
        this.symptomRepo = symptomRepo;
    }

    public Iterable<Symptom> findAll() {
        return this.symptomRepo.findAll();
    }

    public Optional<Symptom> findById(Long id) {
        return this.symptomRepo.findById(id);
    }

    public Optional<Symptom> createSymptom(Symptom symptom) {
        Optional<Symptom> optionalSymptom = this.symptomRepo.findByName(symptom.getName());
        if (optionalSymptom.isPresent()) {
            return Optional.empty();
        }
        Symptom createdSymptom = this.symptomRepo.save(symptom);
        return Optional.of(createdSymptom);
    }

    public Optional<Symptom> findByName(String name) {
        return this.symptomRepo.findByName(name);
    }

    public void deleteSymptom(Optional<Symptom> symptom) {
        symptomRepo.delete(symptom.get());
    }

    public Symptom save(Symptom symptom) {
        return symptomRepo.save(symptom);
    }
}
