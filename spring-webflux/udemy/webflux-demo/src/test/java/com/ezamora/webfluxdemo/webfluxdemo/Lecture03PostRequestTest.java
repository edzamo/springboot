package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class Lecture03PostRequestTest extends BaseTests {

  @Autowired
  private WebClient webClient;

  @Test
  void stepVerifierFluxTest() {
    Mono<Response> responseMono = this.webClient
        .post()
        .uri("/reactive/multiply")
        .bodyValue(buildRequestDto(5, 6))
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectNextMatches(response -> response.getOutPut() == 30)
        .verifyComplete();
  }

  private MultiplyRequestDto buildRequestDto(int a, int b) {
    return new MultiplyRequestDto(a, b);
  }

}
