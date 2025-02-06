package com.ezamora.orderservice.services.dto;

import lombok.Data;

@Data
public class TransactionRequestDto {

  private Integer userId;
  private Integer amount;


}
