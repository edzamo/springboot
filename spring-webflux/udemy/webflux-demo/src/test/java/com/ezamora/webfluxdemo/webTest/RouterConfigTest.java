package com.ezamora.webfluxdemo.webTest;

import com.ezamora.webfluxdemo.config.RequestHandler;
import com.ezamora.webfluxdemo.config.RouterConfig;
import com.ezamora.webfluxdemo.dto.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.ServerResponse;

@WebFluxTest(RouterConfig.class)
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = RouterConfig.class)
class RouterConfigTest {

  private WebTestClient client;
  @Autowired
  private ApplicationContext context;
  @MockBean
  private RequestHandler requestHandler;

  @BeforeAll
  void setClient() {
    this.client = WebTestClient.bindToApplicationContext(context).build();
  }

  @Test
  void test() {
    Mockito.when(requestHandler.squareHandler(Mockito.any()))
        .thenReturn(ServerResponse.ok().bodyValue(new Response(1)));
    this.client
        .get()
        .uri("/router/square/{input}", 15)
        .exchange()
        .expectStatus().is2xxSuccessful()
        .expectBody(Response.class)
        .value(response -> Assertions.assertThat(response.getOutPut()).isEqualTo(1));
  }


}