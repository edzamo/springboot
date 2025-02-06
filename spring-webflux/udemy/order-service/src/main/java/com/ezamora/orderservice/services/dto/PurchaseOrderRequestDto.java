package com.ezamora.orderservice.services.dto;

import lombok.Data;

@Data
public class PurchaseOrderRequestDto {

  private Integer userId;
  private String productId;

}
