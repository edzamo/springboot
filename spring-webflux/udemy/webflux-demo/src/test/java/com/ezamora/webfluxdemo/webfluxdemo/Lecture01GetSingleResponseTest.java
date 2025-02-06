package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.Response;
import java.util.Objects;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture01GetSingleResponseTest extends BaseTests {

  @Autowired
  private WebClient webClient;


  @Test
  void blockTest() {
    Response response = this.webClient.get()
        .uri("reactive/square/{number}", 5)
        .retrieve()
        .bodyToMono(Response.class)
        .block();
    Assertions.assertEquals(Objects.requireNonNull(response).getOutPut(),25);
    System.out.println("response" + response);
  }

  @Test
  void stepVerifierTest() {
    Mono<Response> responseMono = this.webClient.get()
        .uri("reactive/square/{number}", 5)
        .retrieve()
        .bodyToMono(Response.class);

    StepVerifier.create(responseMono)
        .expectNextMatches(response -> response.getOutPut() == 36)
        .expectComplete();
  }

  @Test
  void stepVerifierFluxTest() {
    Flux<Response> responseFlux = this.webClient.get()
        .uri("reactive/table/{number}", 5)
        .retrieve()
        .bodyToFlux(Response.class);

    StepVerifier.create(responseFlux)
        .expectNextCount(10)
        .verifyComplete();
  }

}
