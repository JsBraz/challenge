package com.challenge.challenge.controllers;

import com.challenge.challenge.models.Pathology;
import com.challenge.challenge.models.Patient;
import com.challenge.challenge.services.PathologyService;
import com.challenge.challenge.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/pathology")
public class PathologyController {

    @Autowired
    private PathologyService pathologyService;
    @Autowired
    private PatientService patientService;

    //retieve a pathology by it's ID
    @GetMapping("/pathology/{id}")
    public Optional<Pathology> getPathologyById(@PathVariable Long id) {
        return pathologyService.findById(id);
    }


    //adds a pathology to a doctor
    @PostMapping("/addPathology/{id}")
    public ResponseEntity<Pathology> addPathologyToPatient(@PathVariable Long id, @RequestBody Pathology pathology) {
        Optional<Patient> optionalPatient = patientService.findById(id);
        if (optionalPatient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Patient patient = optionalPatient.get();
        patient.getPathology().add(pathology); // add pathology to patient's list of pathologies
        pathologyService.save(pathology);
        return ResponseEntity.status(HttpStatus.CREATED).body(pathology);
    }
}
