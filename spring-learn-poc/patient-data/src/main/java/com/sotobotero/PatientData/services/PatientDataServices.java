/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sotobotero.PatientData.services;


import com.sotobotero.PatientData.dao.PatientDataRepository;
import com.sotobotero.PatientData.entities.PatientData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sotobotero
 */
@Service
public class PatientDataServices {

  @Autowired
  PatientDataRepository demoRepository;

  public void save(PatientData person) {
    demoRepository.save(person);
  }

  public List<PatientData> getAll() {
    return demoRepository.findAll();
  }

  public PatientData getPatient(long id) {
    return demoRepository.findById(id);
  }

  public List<PatientData> getPatientbyName(String name) {
    return demoRepository.findByName(name);
  }

  public void deletePatient(PatientData patient) {
    demoRepository.delete(patient);
  }


}
