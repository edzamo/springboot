package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture05ThrowsValidationTest extends BaseTests {

  @Autowired
  private WebClient webClient;

  @Test
  void squareInputThrowsTest() {
    Mono<Response> responseMono = webClient.get()
        .uri("reactive-math/square/{input}/throws", 6)
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(System.out::println)
        .doOnError(throwable -> System.out.println("getMessage " + throwable.getMessage()));

    StepVerifier.create(responseMono)
        .verifyError(WebClientResponseException.BadRequest.class);
  }


}
