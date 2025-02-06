package com.example.springWebFlux.entity;


import lombok.Data;

import java.io.Serializable;


@Data
public class Movie implements Serializable{

    private static final long serialVersionUID = 1L;

    private String name;
    private Integer score;

    public Movie(String name, Integer score) {
        this.name = name;
        this.score = score;
    }
}
