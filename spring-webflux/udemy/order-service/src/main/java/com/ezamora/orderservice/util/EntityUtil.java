package com.ezamora.orderservice.util;

import com.ezamora.orderservice.services.dto.RequestContext;
import com.ezamora.orderservice.services.dto.TransactionRequestDto;

public class EntityUtil {

  public  static void setTransactionRequestDto(RequestContext requestContext){
    TransactionRequestDto dto= new TransactionRequestDto();
    dto.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
    dto.setAmount(requestContext.getProductDto().getPrice());
    requestContext.setTransactionRequestDto(dto);
  }
}
