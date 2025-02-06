package com.ezamora.orderservice.services;

import com.ezamora.orderservice.services.dto.PurchaseOrderRequestDto;
import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import reactor.core.publisher.Mono;

public interface OrderFullFillmentService {

  Mono<PurchaseOrderResponseDto> processOrder(Mono<PurchaseOrderRequestDto> requestDtoMono);
}
