package com.pluralsight.productapiannotation.client;

import com.pluralsight.productapiannotation.model.Product;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class WebClientApi {
    private WebClient webClient;

    public WebClientApi() {
        this.webClient = WebClient.builder()
                .baseUrl("http://localhost:8080/products")
                .build();
    }

    private Mono<ResponseEntity<Product>> postNewProduct() {
        return webClient
                .post()
                .body(Mono.just(new Product(null, "Elvin", 1.99)), Product.class)
                .exchangeToMono(clientResponse -> clientResponse.toEntity(Product.class))
                .doOnSuccess(o -> System.out.println("*******Post" + 0));
    }

    public Flux<Product> getAllProduct() {
        return webClient
                .get()
                .retrieve()
                .bodyToFlux(Product.class)
                .doOnNext(o -> System.out.println("**********Get:" + o));
    }

    private Mono<Product> updateProduct(String id, String name, double price) {
        return webClient
                .put()
                .uri("/{id}")
                .body(Mono.just(new Product(null, name, price)), Product.class)
                .retrieve()
                .bodyToMono(Product.class)
                .doOnSuccess(o -> System.out.println("*******Update" + 0));
    }

    private Mono<Void> deleteProduct(String id) {
        return webClient
                .delete()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(o -> System.out.println("*******Update" + 0));
    }

    private Flux<Product> getAllEvent() {
        return webClient
                .get()
                .uri("/events")
                .retrieve()
                .bodyToFlux(Product.class);

    }
}
