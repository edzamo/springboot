/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sotobotero.PatientData.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 *
 * @author sotobotero
 */
@Data
@NoArgsConstructor//default emty constructor
@RequiredArgsConstructor //constructor whit nonnull anoted parameters
@Entity
@Table(name = "patient_data", schema = "public")
public class PatientData implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private long id;
      @NonNull
    private String patientDocumentNumber;
      @NonNull
    private String name;
    
    private String phone;
    
    private String addres;
    
    private String surname;
    
    private Date birdDate;
    
    private String team;
    
    private double weight;
    
    private double size;
    
    private double IMC;
    
    private double folds;
    
    private double perimeters;
    
    private double diameters;
    
    private String allergiesMedical;
    
    private String food;
    
    private String other;
     
    private String pastMedical;
    
    private String surgical;
    
    private String traumatiInjuries;

   
}
