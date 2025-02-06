/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sotobotero.PatientData;

import com.sotobotero.PatientData.api.HabitsSymtomsApi;
import com.sotobotero.PatientData.entities.PatientData;
import com.sotobotero.PatientData.services.PatientDataServices;
import java.util.ArrayList;
import java.util.List;
import org.dozer.DozerBeanMapper;
import static org.hamcrest.Matchers.is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import all static  method from org.springframework.test.web.servlet.result.MockMvcResultMatchers
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//import all static  method from org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


/**
 *
 * @author sotobotero
 */
public class PatientDataApplicationTests extends AbstractIntegrationTest {
    
    //Allowing us to test controller methods simulating a request like a any client would do using a browser or postman
    private MockMvc mockMvc;   
    
    @Mock  //creates a mock implementation for the class it is annotated with.
    PatientDataServices patiendataService;
   
    @Spy //This will allow you to call all the normal methods of the object while still this object is managed by Mockito (as a mock) and injected into a tested service.
    private DozerBeanMapper dozer;
    

    
   @InjectMocks //also creates the mock implementation of annotated type and injects the dependent mocks into it
    private HabitsSymtomsApi patienDataControler;
   
      @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(patienDataControler).build();
    }
    
    @Test
    public void getAllCustomersTest() throws Exception {
        List<PatientData> patients = new ArrayList<>();
        patients.add(new PatientData("123456789", "John Doe"));

        when(patiendataService.getAll()).thenReturn(patients);

        mockMvc.perform(get("/medical/patients")
                .contentType("application/json"))
                .andDo(print())
                .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].patientDocumentNumber", is("123456789")))
             .andExpect(jsonPath("$[0].name", is("John Doe")));
    }
}
