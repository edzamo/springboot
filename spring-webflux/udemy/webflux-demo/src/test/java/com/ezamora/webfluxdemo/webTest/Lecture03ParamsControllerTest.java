package com.ezamora.webfluxdemo.webTest;

import com.ezamora.webfluxdemo.controller.ParamsController;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@WebFluxTest(controllers = {ParamsController.class})
class Lecture03ParamsControllerTest {

  @Autowired
  private WebTestClient client;

  @Test
  void paramsTest() {
    Map<String, Integer> map = Map.of("count", 10, "page", 20);
    this.client
        .get()
        .uri(uriBuilder -> uriBuilder.path("/jobs/search")
            .query("count={count}&page={page}")
            .build(map))
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBodyList(Integer.class)
        .hasSize(2)
        .contains(10,20);

  }
}