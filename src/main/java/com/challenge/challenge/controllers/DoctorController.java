package com.challenge.challenge.controllers;

import com.challenge.challenge.models.Doctor;
import com.challenge.challenge.models.Patient;
import com.challenge.challenge.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Optional;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor/{name}")
    public Optional<Doctor> getDoctorByName(@PathVariable String name) {
        return doctorService.findByName(name);
    }

    @PostMapping("/addDoctor")
    public ResponseEntity<Optional<Doctor>> addDoctor(@RequestBody Doctor doctor) {
        Optional<Doctor> savedDoctor = doctorService.createDoctor(doctor);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDoctor);
    }

    @PostMapping("/addSpecialtyToDoctor")
    public ResponseEntity<String> addSpecialtyToDoctor(@RequestParam Long doctorId, @RequestParam Long specialtyId) {
        doctorService.addSpecialtyToDoctor(doctorId, specialtyId);
        return ResponseEntity.ok("Specialty added to doctor");
    }

}
