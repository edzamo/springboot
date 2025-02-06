package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Lecture09AssignmentTest extends BaseTests {

  private static final String FORMAT = "%d %s %d = %s";
  private static final int A = 10;
  @Autowired
  private WebClient webClient;

  @Test
  void test() {
    System.out.println("test()");
    Flux<String> stringFlux = Flux.range(1, 5)
        .flatMap(integer -> Flux.just("+", "-", "*", "/")
            .flatMap(operation -> send(integer, operation)))
        .doOnNext(s -> System.out.println("..." + s));

    StepVerifier.create(stringFlux)
        .expectNextCount(20)
        .verifyComplete();
  }

  private Mono<String> send(int b, String operation) {
    return this.webClient.get()
        .uri("router/operation/{a}/{b}", A, b)
        .headers(httpHeaders -> httpHeaders.set("operation", operation))
        .retrieve()
        .bodyToMono(Response.class)
        //  .doOnNext(s -> System.out.println("String" + s))
        .map(s -> String.format(FORMAT, A, operation, b, s.getOutPut()));
  }

}
