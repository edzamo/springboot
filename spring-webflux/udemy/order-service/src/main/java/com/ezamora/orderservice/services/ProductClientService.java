package com.ezamora.orderservice.services;

import com.ezamora.orderservice.services.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductClientService {


  Mono<ProductDto> getProductById(String productId);

  Flux<ProductDto> getProducts();
}
