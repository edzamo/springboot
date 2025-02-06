package com.ezamora.productservice.service;

import com.ezamora.productservice.service.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

  public Flux<ProductDto> getAll();

  public Flux<ProductDto> getProductByPriceRange(int min, int max);
  public Mono<ProductDto> getProductById(String productId);
  public Mono<ProductDto> insertProduct(Mono<ProductDto> productDtoMono);
  public Mono<ProductDto> updateProduct(String id, Mono<ProductDto> productDtoMono);
  public Mono<Void> deleteProduct(String id);

}
