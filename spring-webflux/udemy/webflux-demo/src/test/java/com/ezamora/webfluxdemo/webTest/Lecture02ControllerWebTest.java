package com.ezamora.webfluxdemo.webTest;

import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
@AutoConfigureWebTestClient
class Lecture02ControllerWebTest {

  @Autowired
  private WebTestClient client;

  @MockBean
  private ReactiveMathService reactiveMathService;

  @Test
  void stepVerifierMockTest() {
    Mockito.when(reactiveMathService.findSquare(Mockito.anyInt()))
        .thenReturn(Mono.just(new Response(6)));

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
  void stepVerifierMockListTest() {
    Flux<Response> responseFlux = Flux.range(1, 3)
        .map(Response::new);
    Mockito.when(reactiveMathService.multiplicationTable(Mockito.anyInt()))
        .thenReturn(responseFlux);

    this.client
        .get()
        .uri("/reactive/table/{input}", 10)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectHeader().contentType(MediaType.APPLICATION_JSON)
        .expectBodyList(Response.class)
        .hasSize(3);

  }
}
