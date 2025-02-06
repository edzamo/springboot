package com.spring.cloud.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PersonDto {

  private Integer id;
  private String fullName;
  private String address;


}
