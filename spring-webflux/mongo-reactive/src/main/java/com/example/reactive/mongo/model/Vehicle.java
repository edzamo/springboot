package com.example.reactive.mongo.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@Document(collection = "vehicules")
public class Vehicle {

  private String id;
  private String model;
  @Field(name = "cc")
  private Double cubicCentimeters;
  private Integer release;


}
