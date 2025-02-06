package com.ezamora.productservice.service;

import com.ezamora.productservice.service.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {

  private final ProductService productService;


  @Override
  public void run(String... args) throws Exception {
    ProductDto productDto1 = new ProductDto("precioData1", 1001);
    ProductDto productDto2 = new ProductDto("precioData2", 1002);
    ProductDto productDto3 = new ProductDto("precioData3", 1003);
    ProductDto productDto4 = new ProductDto("precioData4", 1004);
    ProductDto productDto5 = new ProductDto("precioData5", 1005);

    Flux.just(productDto1, productDto2, productDto3, productDto4, productDto5)
        .flatMap(productDto ->
            this.productService.insertProduct(Mono.just(productDto)))
        .subscribe(System.out::println);
  }
}
