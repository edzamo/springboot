package com.ezamora.orderservice.services.impl;

import com.ezamora.orderservice.repository.impl.PurchaseOrderRepository;
import com.ezamora.orderservice.services.OrderQueryService;
import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import com.ezamora.orderservice.services.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class OrderQueryServiceImpl implements OrderQueryService {

  private final PurchaseOrderRepository orderRepository;

  @Override
  public Flux<PurchaseOrderResponseDto> getProductByUserId(int userId) {
    return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream())
        .map(TransactionMapper.INSTANCE::toPurchaseOrderResponseDto)
        .subscribeOn(Schedulers.boundedElastic());
  }
}
