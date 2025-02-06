package com.example.reactive.postgres.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("vehicules")
public class Vehicle {

  @Id
  private Long id;
  private String model;
  @Column("cc")
  private Double cubicCentimeters;
  private Integer release;


}
