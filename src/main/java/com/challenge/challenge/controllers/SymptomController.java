package com.challenge.challenge.controllers;

import com.challenge.challenge.models.Pathology;
import com.challenge.challenge.models.Patient;
import com.challenge.challenge.models.Symptom;
import com.challenge.challenge.repository.SymptomRepo;
import com.challenge.challenge.services.PathologyService;
import com.challenge.challenge.services.SymptomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping( "/symptom")
public class SymptomController {

    @Autowired
    private SymptomService symptomService;
    @Autowired
    private PathologyService pathologyService;

    @GetMapping("/symptom/{id}")
    public Optional<Symptom> getSymptomById(@PathVariable Long id) {

        return symptomService.findById(id);
    }

    //adds a symptom to a patholgy
    @PostMapping("/addSymptom/{id}")
    public ResponseEntity<Symptom> addSymptomToPathology(@PathVariable Long id, @RequestBody Symptom symptom) {
        Optional<Pathology> optionalPathology = pathologyService.findById(id);
        if (optionalPathology.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        Pathology pathology = optionalPathology.get();
        pathology.getSymptom().add(symptom); // add pathology to patient's list of pathologies
        symptomService.save(symptom);
        return ResponseEntity.status(HttpStatus.CREATED).body(symptom);
    }
}

