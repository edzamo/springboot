package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.InputFailedValidationResponse;
import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture06ExchangeTest extends BaseTests {

  @Autowired
  private WebClient webClient;

  // Exchange = retrieve + additional  info http status code
  @Test
  void squareInputThrowsTest() {
    Mono<Object> responseMono = webClient.get()
        .uri("reactive-math/square/{input}/throws", 6)
        .exchangeToMono(this::exchange)
        .doOnNext(System.out::println)
        .doOnError(throwable -> System.out.println("getMessage " + throwable.getMessage()));

    StepVerifier.create(responseMono)
        .expectNextCount(1)
        .verifyComplete();
  }

  private Mono<Object> exchange(ClientResponse cr) {
    if (cr.statusCode().value() == 400) {
      return cr.bodyToMono(InputFailedValidationResponse.class);
    } else {
      return cr.bodyToMono(Response.class);
    }
  }
}

