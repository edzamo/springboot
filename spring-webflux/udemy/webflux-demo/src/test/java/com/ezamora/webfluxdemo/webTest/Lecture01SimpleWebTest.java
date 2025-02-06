package com.ezamora.webfluxdemo.webTest;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
@AutoConfigureWebTestClient
class Lecture01SimpleWebTest {

  @Autowired
  private WebTestClient client;

  @Mock
  private ReactiveMathService reactiveMathService;

  @Test
  void stepVerifierTest() {
    Flux<Response> responseFlux = this.client
        .get()
        .uri("/reactive-math/square/{input}/assignment", 10)
        .exchange()
        .expectStatus().is2xxSuccessful()
        //  .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .returnResult(Response.class)
        .getResponseBody();

    StepVerifier.create(responseFlux)
        .expectNextMatches(response -> response.getOutPut() == 100)
        .verifyComplete();
  }

  @Test
  void stepVerifierAssertionTest() {
    Flux<Response> responseFlux = this.client
        .get()
        .uri("/reactive-math/square/{input}/assignment", 10)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .returnResult(Response.class)
        .getResponseBody();

    StepVerifier.create(responseFlux)
        .assertNext(response -> {
          assertThat(response.getOutPut()).isEqualTo(100);
        })
        .verifyComplete();
  }

  @Test
  void stepVerifierAssertionSecondWayTest() {
    this.client
        .get()
        .uri("/reactive-math/square/{input}/assignment", 10)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Response.class)
        .value(response -> Assertions.assertThat(response.getOutPut()).isEqualTo(100));

  }

  @Test
  void stepVerifierMockTest() {
    Mockito.when(reactiveMathService.findSquare(Mockito.anyInt()))
        .thenReturn(Mono.just(new Response(0)));

    this.client
        .get()
        .uri("/reactive-math/square/{input}/assignment", 10)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBody(Response.class)
        .value(response -> Assertions.assertThat(response.getOutPut()).isEqualTo(100));

  }
}
