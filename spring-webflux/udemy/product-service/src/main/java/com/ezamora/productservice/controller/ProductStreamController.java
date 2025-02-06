package com.ezamora.productservice.controller;

import com.ezamora.productservice.service.dto.ProductDto;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductStreamController {

  private final Flux<ProductDto> flux;

  @GetMapping(value = "stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  public Flux<ProductDto> getProductUpdate(){
    return this.flux;
  }

}
