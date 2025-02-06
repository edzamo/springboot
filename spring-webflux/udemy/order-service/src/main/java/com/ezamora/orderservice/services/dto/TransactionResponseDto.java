package com.ezamora.orderservice.services.dto;

import com.ezamora.orderservice.domain.enums.TransactionsStatus;
import lombok.Data;

@Data
public class TransactionResponseDto {

  private Integer userId;
  private Integer amount;
  private TransactionsStatus status;
}
