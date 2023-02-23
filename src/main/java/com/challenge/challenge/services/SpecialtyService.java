package com.challenge.challenge.services;

import com.challenge.challenge.models.Specialty;
import com.challenge.challenge.models.Symptom;
import com.challenge.challenge.repository.SpecialtyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecialtyService {

    private SpecialtyRepo specialtyRepo;

    public SpecialtyService(SpecialtyRepo specialtyRepo) {
        this.specialtyRepo = specialtyRepo;
    }

    public Optional<Specialty> findById(Long id) {
        return this.specialtyRepo.findById(id);
    }

    public Optional<Specialty> createSpecialty(Specialty specialty) {
        Optional<Specialty> optionalSpecialty = this.specialtyRepo.findById(specialty.getId());
        if(optionalSpecialty.isPresent()){
            return Optional.empty();
        }
        Specialty createdSpecialty = this.specialtyRepo.save(specialty);
        return Optional.of(createdSpecialty);
    }
}
