package com.pluralsight.rxjava.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpaceShip {

    private Integer id;
    private String name;
    private Integer amount ;

    public SpaceShip(String name, Integer amount) {
        this.name = name;
        this.amount = amount;
    }
}
