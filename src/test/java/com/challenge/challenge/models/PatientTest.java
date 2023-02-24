package com.challenge.challenge.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PatientTest {

    @Test
    public void testPatientConstructor() {
        String name = "John Smith";
        Integer age = 35;
        List<Pathology> pathologyList = new ArrayList<>();

        Patient patient = new Patient(name, age);
        patient.setPathology(pathologyList);

        Assertions.assertEquals(name, patient.getName());
        Assertions.assertEquals(age, patient.getAge());
        Assertions.assertEquals(pathologyList, patient.getPathology());
    }

    @Test
    public void testPatientSetters() {
        String name = "John Smith";
        Integer age = 35;
        List<Pathology> pathologyList = new ArrayList<>();

        Patient patient = new Patient();
        patient.setName(name);
        patient.setAge(age);
        patient.setPathology(pathologyList);

        Assertions.assertEquals(name, patient.getName());
        Assertions.assertEquals(age, patient.getAge());
        Assertions.assertEquals(pathologyList, patient.getPathology());
    }

    @Test
    public void testPatientId() {
        Long id = 1L;

        Patient patient = new Patient();
        patient.setId(id);

        Assertions.assertEquals(id, patient.getId());
    }
}
