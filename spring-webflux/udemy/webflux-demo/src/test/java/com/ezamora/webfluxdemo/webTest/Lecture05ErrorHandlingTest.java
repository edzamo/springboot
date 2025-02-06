package com.ezamora.webfluxdemo.webTest;

import com.ezamora.webfluxdemo.controller.ReactiveMathValidationController;
import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@WebFluxTest(ReactiveMathValidationController.class)
class Lecture05ErrorHandlingTest {

  @Autowired
  private WebTestClient client;
  @MockBean
  private ReactiveMathService reactiveMathService;

  @Test
  void throwsTest(){

    Mockito.when(reactiveMathService.findSquare(Mockito.anyInt()))
        .thenReturn(Mono.just(new Response(1)));
    this.client
        .get()
        .uri("/reactive-math/square/{input}/throws",5)
        .exchange()
        .expectStatus().isBadRequest()
        .expectBody()
        .jsonPath("$.message").isEqualTo("allowed range is 10 - 20")
        .jsonPath("$.errorCode").isEqualTo(100)
        .jsonPath("$.input").isEqualTo(5);

  }

  }



