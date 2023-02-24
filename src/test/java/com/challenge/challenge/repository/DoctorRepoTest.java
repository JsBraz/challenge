package com.challenge.challenge.repository;

import com.challenge.challenge.models.Doctor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DoctorRepoTest {

    @Mock
    private DoctorRepo doctorRepo;

    @Test
    public void testFindByName() {
        // create a test doctor object
        Doctor testDoctor = new Doctor();
        testDoctor.setName("Test Doctor");

        // mock the behavior of the repository
        when(doctorRepo.findByName("Test Doctor")).thenReturn(Optional.of(testDoctor));

        // verify that the repository returns the expected doctor object
        Optional<Doctor> foundDoctor = doctorRepo.findByName("Test Doctor");
        assertTrue(foundDoctor.isPresent());
        assertEquals(testDoctor, foundDoctor.get());
    }
}

