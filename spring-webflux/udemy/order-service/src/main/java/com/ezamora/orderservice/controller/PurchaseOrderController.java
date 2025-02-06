package com.ezamora.orderservice.controller;


import com.ezamora.orderservice.services.OrderFullFillmentService;
import com.ezamora.orderservice.services.OrderQueryService;
import com.ezamora.orderservice.services.dto.PurchaseOrderRequestDto;
import com.ezamora.orderservice.services.dto.PurchaseOrderResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("order")
@RequiredArgsConstructor
public class PurchaseOrderController {

  private final OrderFullFillmentService orderFullFillmentService;
  private final OrderQueryService orderQueryService;

  @PostMapping
  public Mono<ResponseEntity<PurchaseOrderResponseDto>> order(
      @RequestBody Mono<PurchaseOrderRequestDto> requestDtoMono) {
    return this.orderFullFillmentService.processOrder(requestDtoMono)
        .map(ResponseEntity::ok)
        .onErrorReturn(WebClientException.class,ResponseEntity.badRequest().build())
        .onErrorReturn(WebClientException.class,ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build());
  }

  @GetMapping("user/{userId}")
  public Flux<PurchaseOrderResponseDto> getOrderByUserID(@PathVariable int userId) {
    return this.orderQueryService.getProductByUserId(userId);
  }

}


