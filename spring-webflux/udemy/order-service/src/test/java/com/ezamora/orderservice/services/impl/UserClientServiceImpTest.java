package com.ezamora.orderservice.services.impl;

import com.ezamora.orderservice.services.OrderFullFillmentService;
import com.ezamora.orderservice.services.ProductClientService;
import com.ezamora.orderservice.services.UserClientService;
import com.ezamora.orderservice.services.dto.ProductDto;
import com.ezamora.orderservice.services.dto.PurchaseOrderRequestDto;
import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import com.ezamora.orderservice.services.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
class UserClientServiceImpTest {

  @Autowired
  private UserClientService userClientService;
  @Autowired
  private ProductClientService productClientService;
  @Autowired
  private OrderFullFillmentService orderFullFillmentService;

  @Test
  void contextLoads(){
    Flux<PurchaseOrderResponseDto> dtoFlux = Flux.zip(
            this.userClientService.getAllUsers(), this.productClientService.getProducts())
        .map(t -> buildDto(t.getT1(), t.getT2()))
        .flatMap(purchaseOrderRequestDto -> this.orderFullFillmentService.processOrder(
            Mono.just(purchaseOrderRequestDto)))
        .doOnNext(System.out::println);

    StepVerifier.create(dtoFlux)
        .expectNextCount(3)
        .verifyComplete();
  }

  private PurchaseOrderRequestDto buildDto(UserDto userDto, ProductDto productDto) {
    PurchaseOrderRequestDto dto= new PurchaseOrderRequestDto();
    dto.setUserId(userDto.getId());
    dto.setProductId(productDto.getId());
    return dto;
  }

}