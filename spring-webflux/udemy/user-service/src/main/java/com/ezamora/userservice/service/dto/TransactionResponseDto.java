package com.ezamora.userservice.service.dto;

import com.ezamora.userservice.domain.enums.TransactionsStatus;
import lombok.Data;

@Data
public class TransactionResponseDto {

  private Integer userId;
  private Integer amount;
  private TransactionsStatus status;
}
