package com.ezamora.orderservice.services.mapper;

import com.ezamora.orderservice.domain.PurchaseOrder;
import com.ezamora.orderservice.domain.enums.OrderStatus;
import com.ezamora.orderservice.domain.enums.TransactionsStatus;
import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import com.ezamora.orderservice.services.dto.RequestContext;
import com.ezamora.orderservice.services.dto.TransactionRequestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);


  default void toTransactionRequestDto(RequestContext requestContext) {
    TransactionRequestDto dto = new TransactionRequestDto();
    dto.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
    dto.setAmount(requestContext.getProductDto().getPrice());
    requestContext.setTransactionRequestDto(dto);
  }

  default PurchaseOrder toPurchaseOrder(RequestContext requestContext) {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setId(requestContext.getPurchaseOrderRequestDto().getUserId());
    purchaseOrder.setUserId(requestContext.getPurchaseOrderRequestDto().getUserId());
    purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDto().getProductId());
    purchaseOrder.setAmount(requestContext.getProductDto().getPrice());
    purchaseOrder.setId(requestContext.getPurchaseOrderRequestDto().getUserId());

    purchaseOrder.setStatus(
        (requestContext.getTransactionResponseDto().getStatus() == TransactionsStatus.APPROVED) ?
            OrderStatus.COMPLETED : OrderStatus.FAILED);
    return purchaseOrder;
  }


  @Mapping(target = "orderId", source = "purchaseOrder.id")
  PurchaseOrderResponseDto toPurchaseOrderResponseDto(PurchaseOrder purchaseOrder);


}
