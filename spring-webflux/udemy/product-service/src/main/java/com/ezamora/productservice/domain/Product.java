package com.ezamora.productservice.domain;

import java.io.Serializable;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
public class Product implements Serializable {

  @Id
  private String id;
  private String description;
  private Integer price;


}
