package com.reactive.mongodb.reactivemongodb.model;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Customer {

  @Id
  public String id;

  public String firstName;
  public String lastName;


}
