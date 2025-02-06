/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sotobotero.PatientData.api;

import com.sotobotero.PatientData.dto.PatientDataRequest;
import com.sotobotero.PatientData.entities.PatientData;
import com.sotobotero.PatientData.services.PatientDataServices;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.dozer.Mapper;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author sotobotero
 */

@RestController
@RequestMapping(value = "/medical/patients")
public class HabitsSymtomsApi {

  @Autowired
  PatientDataServices demosServices;
  @Autowired
  Mapper mapper;


  @RequestMapping(method = RequestMethod.GET)
  public List<PatientDataRequest> getPerson() {
    List<PatientData> all = demosServices.getAll();
    List<PatientDataRequest> list = new LinkedList<>();
    for (PatientData dto : all) {
      PatientDataRequest map = mapper.map(dto, PatientDataRequest.class);
      list.add(map);
    }
    return list;
  }

  @RequestMapping(method = RequestMethod.POST)
  public void savePerson(@RequestBody PatientDataRequest personRequest) {
    PatientData map = mapper.map(personRequest, PatientData.class);
    demosServices.save(map);
  }

  @RequestMapping(method = RequestMethod.PUT)
  public void updatePerson(@RequestBody PatientDataRequest personRequest) {
    PatientData map = mapper.map(personRequest, PatientData.class);
    demosServices.save(map);
  }


  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public PatientDataRequest getById(@PathVariable("id") long id) {
    PatientData patient = demosServices.getPatient(id);
      if (patient != null) {
          PatientDataRequest map = mapper.map(patient, PatientDataRequest.class);
          return map;
      } else {
          return null;
      }
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  public void deletePerson(@PathVariable long id) {
    demosServices.deletePatient(demosServices.getPatient(id));
  }


  @RequestMapping(value = "/find", method = RequestMethod.GET)
  public List<PatientDataRequest> getPatient(@RequestParam("name") String name) {
    List<PatientDataRequest> list = new ArrayList<>();
    List<PatientData> patientbyName = demosServices.getPatientbyName(name);
    for (PatientData dto : patientbyName) {
      list.add(mapper.map(dto, PatientDataRequest.class));
    }
    return list;
  }

}
