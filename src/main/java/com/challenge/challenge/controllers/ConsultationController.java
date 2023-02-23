package com.challenge.challenge.controllers;

import com.challenge.challenge.models.*;
import com.challenge.challenge.services.ConsultationService;
import com.challenge.challenge.services.DoctorService;
import com.challenge.challenge.services.PatientService;
import com.challenge.challenge.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;
    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private SpecialtyService specialtyService;

    @GetMapping("/consultation/{id}")
    public Optional<Consultation> getConsultationById(@PathVariable Long id) {
        return consultationService.findById(id);
    }

    //schedules a consultation between a doctor with a determined speciality and a patient -- REQUESTED ENDPOINT IN THE CHALLENGE
    @GetMapping("/addConsultation")
    public ResponseEntity<String> addConsultation(@RequestParam Long patientId,
                                                  @RequestParam Long doctorId,
                                                  @RequestParam Long specialtyId) {
        consultationService.addConsultation(patientId, doctorId, specialtyId);
        return ResponseEntity.ok("Consultation scheduled");
    }

    //returns a list of alll of the consultations a patient has, and relative data -- REQUESTED ENDPOINT IN THE CHALLENGE
    @GetMapping("/patientConsultations")
    public List<Consultation> getPatientConsultations(@RequestParam Long patientId) {
        return consultationService.findByPatientId(patientId);
    }

    //Retrieves specialities that have more than 2 unique patients -- REQUESTED ENDPOINT IN THE CHALLENGE
    @GetMapping("/specialtiesWithMoreThan2Patients")
    public List<Object[]> findSpecialtiesWithMoreThan2Patients() {
        return consultationService.findSpecialtiesWithMoreThan2Patients();
    }
}