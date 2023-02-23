package com.challenge.challenge.controllers;

import com.challenge.challenge.models.Patient;
import com.challenge.challenge.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;


    /*
    retrieves the data of all the patients with the requested features - REQUESTED ENDPOINT IN THE CHALLENGE

    The implementation first checks if both name and age parameters are provided.
    If so, it calls the findByNameContainingIgnoreCaseAndAge method of the PatientService to retrieve the matching patients.
    If only name is provided, it calls the findByNameContainingIgnoreCase method.
    If only age is provided, it calls the findByAge method.
    Finally, if neither parameter is provided, it calls the findAll method to retrieve all patients.
     */
    @GetMapping("/allPatients")
    public Iterable<Patient> getAllPatients(
            @RequestParam(name = "name", required = false) String name, //filters the patients whose names contain the given string
            @RequestParam(name = "age", required = false) Integer age,  //filters the patients whose age matches the given integer
            @RequestParam(name = "page", defaultValue = "0") int page,  //specifies the page number to retrieve (default is 0)
            @RequestParam(name = "size", defaultValue = "10") int size, //specifies the number of items to retrieve per page (default is 10)
            @RequestParam(name = "sort", defaultValue = "name") String sort //specifies the field to sort by (default is "name"). If the field name starts with a "-", the sort order is descending
    ) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (sort.startsWith("-")) {
            direction = Sort.Direction.DESC;
            sort = sort.substring(1);
        }
        Sort.Order order = new Sort.Order(direction, sort);
        Pageable pageable = PageRequest.of(page, size, Sort.by(order));
        if (name != null && age != null) {
            return patientService.findByNameContainingIgnoreCaseAndAge(name, age, pageable);
        } else if (name != null) {
            return patientService.findByNameContainingIgnoreCase(name, pageable);
        } else if (age != null) {
            return patientService.findByAge(age, pageable);
        } else {
            return patientService.findAll(pageable);
        }
    }

    @GetMapping("/patient/{id}")
    public Optional<Patient> getPatientById(@PathVariable Long id) {
        return patientService.findById(id);
    }

    @PostMapping("/addPatient")
    public ResponseEntity<Optional<Patient>> addPatient(@RequestBody Patient patient) {
        Optional<Patient> savedPatient = patientService.createPatient(patient);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPatient);
    }

    @PutMapping("/patient/{id}")
    public ResponseEntity<Optional<Patient>> updatePatient(@RequestBody Patient patient, @PathVariable Long id) {
        Optional<Patient> existingPatient = patientService.findById(id);
        if (existingPatient.isPresent()) {
            patient.setId(id);
            Optional<Patient> updatedPatient = patientService.createPatient(patient);
            if(updatedPatient.isPresent()) {
                return ResponseEntity.ok(updatedPatient);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/patient/{id}")
    public ResponseEntity<Object> deletePatient(@PathVariable Long id) {
        Optional<Patient> patient = patientService.findById(id);
        if (patient.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        patientService.deletePatient(patient);
        return ResponseEntity.ok().build();
    }
    }
