package com.ezamora.orderservice.services.impl;

import com.ezamora.orderservice.services.ProductClientService;
import com.ezamora.orderservice.services.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductClientServiceImpl implements ProductClientService {


  private final WebClient webClient;
  @Value("${product.service.url}")
  private String injectedPropertyUrl;
  /*
  @Autowired
  public ProductClientServiceImpl(WebClient webClient) {
    this.webClient = webClient;
  }
  public ProductClientServiceImpl(@Value("{product.service.url}") String url) {
    System.out.println("url: " +url.toString());
    this.webClient = WebClient
        .builder()
        .baseUrl(url)
        .build();
  }
*/

  @Override
  public Mono<ProductDto> getProductById(final String productId) {
    System.out.println("ProductClientService " + productId + " "+ this.webClient.get()) ;
    return this.webClient.get()
        .uri(injectedPropertyUrl.concat("{id}"), productId)
        .retrieve()
        .bodyToMono(ProductDto.class);
  }

  @Override
  public Flux<ProductDto> getProducts() {
    return this.webClient.get()
        .uri(injectedPropertyUrl.concat("all"))
        .retrieve()
        .bodyToFlux(ProductDto.class);
  }
}
