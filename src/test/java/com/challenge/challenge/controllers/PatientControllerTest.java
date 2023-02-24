package com.challenge.challenge.controllers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Optional;

import com.challenge.challenge.models.Patient;
import com.challenge.challenge.services.PatientService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {


    /*
    issues with the class regarding the patientService mocking, left unresolved due to time constraints
     */
    private MockMvc mockMvc;

    @Mock
    private PatientService patientService;

    public PatientControllerTest() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new PatientController(patientService)).build();
    }

    @Test
    public void getAllPatients() throws Exception {
        PageRequest pageRequest = PageRequest.of(0, 10, Sort.by("name"));
        Iterable<Patient> patients = new PageImpl<>(Arrays.asList(new Patient("John", 20), new Patient("Mary", 30)), pageRequest, 2);
        when(patientService.findAll(pageRequest)).thenReturn(patients);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patients/allPatients").param("sort", "name");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        int status = response.getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals("[{\"id\":null,\"name\":\"John\",\"age\":20},{\"id\":null,\"name\":\"Mary\",\"age\":30}]", content);
    }

    @Test
    public void getPatientById() throws Exception {
        Long patientId = 1L;
        Optional<Patient> patient = Optional.of(new Patient("John", 20));
        when(patientService.findById(patientId)).thenReturn(patient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/patients/patient/" + patientId);

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        int status = response.getStatus();

        assertEquals(HttpStatus.OK.value(), status);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals("{\"id\":null,\"name\":\"John\",\"age\":20}", content);
    }

    @Test
    public void addPatient() throws Exception {
        Patient patient = new Patient("John", 20);
        Optional<Patient> savedPatient = Optional.of(patient);
        when(patientService.createPatient(patient)).thenReturn(savedPatient);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/patients/addPatient")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"John\",\"age\":20}");

        ResultActions resultActions = mockMvc.perform(requestBuilder);
        MvcResult result = resultActions.andReturn();
        MockHttpServletResponse response = result.getResponse();
        String content = response.getContentAsString();
        int status = response.getStatus();

        assertEquals(HttpStatus.CREATED.value(), status);
        assertEquals(MediaType.APPLICATION_JSON_VALUE, response.getContentType());
        assertEquals("{\"id\":null,\"name\":\"John\",\"age\":20}", content);
    }
}
