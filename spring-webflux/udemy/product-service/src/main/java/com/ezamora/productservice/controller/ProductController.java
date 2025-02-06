package com.ezamora.productservice.controller;

import com.ezamora.productservice.service.ProductService;
import com.ezamora.productservice.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService productService;

  @GetMapping("all")
  public Flux<ProductDto> getAll() {
    return this.productService.getAll();
  }

  @GetMapping("price-range")
  public Flux<ProductDto> getProductById(@RequestParam("max") Integer max,
      @RequestParam("min") Integer min) {
    return this.productService.getProductByPriceRange(min, max);
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<ProductDto>> getProductById(@PathVariable String id) {
    System.out.println("product-service  ProductService " + id);
    return this.productService.getProductById(id)
        .doOnNext(System.out::println)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<ProductDto>> insertProduct(
      @RequestBody Mono<ProductDto> productDtoMono) {
    return this.productService.insertProduct(productDtoMono)
        .map(ResponseEntity::ok);
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<ProductDto>> updateProduct(@PathVariable String id,
      @RequestBody Mono<ProductDto> productDtoMono) {
    return this.productService.updateProduct(id, productDtoMono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteProductById(@PathVariable String id) {
    return this.productService.deleteProduct(id);
  }

}
