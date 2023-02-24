package com.challenge.challenge.models;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ConsultationTest {

    @Test
    public void testConstructorAndGetters() {
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        Specialty specialty = new Specialty("Oftalmology");
        Consultation consultation = new Consultation(doctor, patient, specialty);
        assertEquals(doctor, consultation.getDoctor());
        assertEquals(patient, consultation.getPatient());
        assertEquals(specialty, consultation.getSpecialty());
    }

    @Test
    public void testSetters() {
        Doctor doctor = new Doctor();
        Patient patient = new Patient();
        Specialty specialty = new Specialty("Oftalmology");
        Consultation consultation = new Consultation();
        consultation.setDoctor(doctor);
        consultation.setPatient(patient);
        consultation.setSpecialty(specialty);
        assertEquals(doctor, consultation.getDoctor());
        assertEquals(patient, consultation.getPatient());
        assertEquals(specialty, consultation.getSpecialty());
    }
}
