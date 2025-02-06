package com.ezamora.userservice.service.dto;

import lombok.Data;

@Data
public class UserTransactionDto {

  private Integer userId;
  private Integer amount;
  private String transactionDate;
}
