/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sotobotero.PatientData.dto;

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
public class PatientDataRequest implements Serializable {

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
