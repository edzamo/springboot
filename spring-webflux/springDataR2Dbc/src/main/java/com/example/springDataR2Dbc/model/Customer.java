package com.example.springDataR2Dbc.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class Customer {

    @Id
    private Integer id;
    private String firstname;
    private String lastname;

}
