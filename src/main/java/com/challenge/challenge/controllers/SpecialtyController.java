package com.challenge.challenge.controllers;

import com.challenge.challenge.models.Specialty;
import com.challenge.challenge.services.SpecialtyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/specialties")
public class SpecialtyController {

    @Autowired
    private SpecialtyService specialtyService;

    @PostMapping("/addSpecialty")
    public ResponseEntity<Optional<Specialty>> addSpecialty(@RequestBody Specialty specialty) {
        Optional<Specialty> savedSpecialty = specialtyService.createSpecialty(specialty);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSpecialty);
    }

}
