package com.challenge.challenge.services;

import com.challenge.challenge.models.Consultation;
import com.challenge.challenge.models.Doctor;
import com.challenge.challenge.models.Patient;
import com.challenge.challenge.models.Specialty;
import com.challenge.challenge.repository.ConsultationRepo;
import com.challenge.challenge.repository.DoctorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationService {
    private ConsultationRepo consultationRepo;
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecialtyService specialtyService;


    @Autowired
    public ConsultationService(ConsultationRepo consultationRepo){
        this.consultationRepo = consultationRepo;
    }
    public Iterable<Consultation> findAll() {
        return this.consultationRepo.findAll();
    }

    public Optional<Consultation> findById(Long id) {
        return this.consultationRepo.findById(id);
    }

    public Optional<Consultation> findByID(Long id) {
        return this.consultationRepo.findById(id);
    }

    public void deleteConsultation(Optional<Consultation> consultation) {
        consultationRepo.delete(consultation.get());
    }

    public Consultation addConsultation(
            Long patientId,
            Long doctorId,
            Long specialtyId
    ) {
        Optional<Patient> patient = patientService.findById(patientId);
        Optional<Doctor> doctor = doctorService.findById(doctorId);
        Optional<Specialty> specialty = specialtyService.findById(specialtyId);

        if (patient.isEmpty() || doctor.isEmpty() || specialty.isEmpty()) { //check if any parameter is missing
            throw new IllegalArgumentException("Invalid patient, doctor or specialty ID");
        }

        if (!doctor.get().getSpecialty().equals(specialty.get())) {     //if the doctors specialty isnt the same as the consultation you wont be allowed to shcedule the appointment
            throw new IllegalArgumentException("The doctor does not have the required specialty");
        }

        Consultation consultation = new Consultation();
        consultation.setPatient(patient.get());
        consultation.setDoctor(doctor.get());
        consultation.setSpecialty(specialty.get());
        return consultationRepo.save(consultation);
    }

    public List<Consultation> findByPatientId(Long patientId) {
        return consultationRepo.findByPatientId(patientId);
    }

    public List<Object[]> findSpecialtiesWithMoreThan2Patients() {
        return consultationRepo.findSpecialtiesWithMoreThan2Patients();
    }
}


