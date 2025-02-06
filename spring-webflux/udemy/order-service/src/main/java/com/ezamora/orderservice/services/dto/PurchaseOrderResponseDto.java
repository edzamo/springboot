package com.ezamora.orderservice.services.dto;

import com.ezamora.orderservice.domain.enums.OrderStatus;
import lombok.Data;

@Data
public class PurchaseOrderResponseDto {

  private Integer orderId;
  private String userId;
  private String productId;
  private Integer amount;
  private OrderStatus status;
  
}
