package com.ezamora.orderservice.services;

import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import reactor.core.publisher.Flux;

public interface OrderQueryService {

  Flux<PurchaseOrderResponseDto> getProductByUserId(int userId);
}
