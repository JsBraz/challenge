package com.challenge.challenge.services;

import com.challenge.challenge.models.Doctor;
import com.challenge.challenge.models.Pathology;
import com.challenge.challenge.models.Specialty;
import com.challenge.challenge.repository.DoctorRepo;
import com.challenge.challenge.repository.PathologyRepo;
import com.challenge.challenge.repository.SpecialtyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import javax.print.Doc;
import java.util.Optional;

@Service
public class DoctorService {

    private DoctorRepo doctorRepo;

    @Autowired
    private SpecialtyRepo specialtyRepo;

    @Autowired
    public DoctorService(DoctorRepo doctorRepo){
        this.doctorRepo = doctorRepo;
    }
    public Iterable<Doctor> findAll() {
        return this.doctorRepo.findAll();
    }

    public Optional<Doctor> findById(Long id) {
        return this.doctorRepo.findById(id);
    }

    public Optional<Doctor> createDoctor(Doctor doctor) {
        Optional<Doctor> optionalDoctor= this.doctorRepo.findByName(doctor.getName());
        if(optionalDoctor.isPresent()){
            return Optional.empty();
        }
        Doctor createdDoctor = this.doctorRepo.save(doctor);
        return Optional.of(createdDoctor);
    }

    public Optional<Doctor> findByName(String name) {
        return this.doctorRepo.findByName(name);
    }

    public void deleteDoctor(Optional<Doctor> doctor) {
        doctorRepo.delete(doctor.get());
    }

    @Transactional
    public void addSpecialtyToDoctor(Long doctorId, Long specialtyId) {
        Optional<Doctor> optionalDoctor = doctorRepo.findById(doctorId);        //retieve doctor
        Optional<Specialty> optionalSpecialty = specialtyRepo.findById(specialtyId);    //retrieve specialty
        if (optionalDoctor.isPresent() && optionalSpecialty.isPresent()) {
            Doctor doctor = optionalDoctor.get();
            Specialty specialty = optionalSpecialty.get();
            doctor.setSpecialty(specialty);
            doctorRepo.save(doctor);        //saves updated Doctor object to the database using the doctor repo
        } else {
            throw new EntityNotFoundException("Doctor or Specialty not found");
        }
    }
}
