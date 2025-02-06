package com.ezamora.webfluxdemo.webTest;

import com.ezamora.webfluxdemo.controller.ReactiveMatchController;
import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(ReactiveMatchController.class)
class Lecture04PostTest {

  @Autowired
  private WebTestClient client;
  @MockBean
  private ReactiveMathService reactiveMathService;

  @Test
  void postTest() {
    Mono<Response> responseMono = Mono.just(new Response(9));
    Mockito.when(reactiveMathService.multiply(Mockito.any()))
        .thenReturn(responseMono);

    this.client
        .post()
        .uri("/reactive/multiply")
        .accept(MediaType.APPLICATION_JSON)
        .headers(httpHeaders -> httpHeaders.setBasicAuth("username", "password"))
        .headers(httpHeaders -> httpHeaders.set("somevalue", "somevalue"))
       // .bodyValue(new MultiplyRequestDto())
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(Response.class)
        .value(response -> {
          Assertions.assertThat(response.getOutPut()).isEqualTo(9);
        });
  }


}
