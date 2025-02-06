package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture02GetSingleResponseTest extends BaseTests {

  @Autowired
  private WebClient webClient;




  @Test
  void stepVerifierFluxTest() {
    Flux<Response> responseFlux = this.webClient.get()
        .uri("/router/table/{number}/stream", 5)
        .retrieve()
        .bodyToFlux(Response.class);

    StepVerifier.create(responseFlux)
        .expectNextCount(10)
        .verifyComplete();
  }

}
