package com.challenge.challenge.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.challenge.challenge.models.Consultation;
import com.challenge.challenge.services.ConsultationService;
import com.challenge.challenge.services.DoctorService;
import com.challenge.challenge.services.PatientService;
import com.challenge.challenge.services.SpecialtyService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ConsultationControllerTest {
    private MockMvc mockMvc;

    @Mock
    private ConsultationService consultationService;

    @Mock
    private PatientService patientService;

    @Mock
    private DoctorService doctorService;

    @Mock
    private SpecialtyService specialtyService;

    @InjectMocks
    private ConsultationController consultationController;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.standaloneSetup(consultationController).build();
    }

    @Test
    void getConsultationById_ReturnsConsultation() throws Exception {
        Long consultationId = 1L;
        Consultation consultation = new Consultation();
        consultation.setId(consultationId);
        consultation.setDoctor(null);
        consultation.setPatient(null);

        when(consultationService.findById(consultationId)).thenReturn(Optional.of(consultation));

        mockMvc.perform(MockMvcRequestBuilders.get("/consultations/consultation/" + consultationId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(consultationId))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void addConsultation_ReturnsOk() throws Exception {
        Long patientId = 1L;
        Long doctorId = 2L;
        Long specialtyId = 3L;

        mockMvc.perform(MockMvcRequestBuilders.get("/consultations/addConsultation")
                        .param("patientId", patientId.toString())
                        .param("doctorId", doctorId.toString())
                        .param("specialtyId", specialtyId.toString()))
                .andExpect(status().isOk())
                .andExpect(content().string("Consultation scheduled"));
    }

    @Test
    void getPatientConsultations_ReturnsConsultations() throws Exception {
        Long patientId = 1L;
        Consultation consultation1 = new Consultation();
        Consultation consultation2 = new Consultation();
        List<Consultation> consultations = Arrays.asList(consultation1, consultation2);

        when(consultationService.findByPatientId(patientId)).thenReturn(consultations);

        mockMvc.perform(MockMvcRequestBuilders.get("/consultations/patientConsultations")
                        .param("patientId", patientId.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

}
