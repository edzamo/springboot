package com.ezamora.webfluxdemo.webfluxdemo;

import static org.assertj.core.api.Assertions.assertThat;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import java.time.ZonedDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture04HeaderTest extends BaseTests {

  @Autowired
  private WebClient webClient;

  @Test
  void stepVerifierFluxTest() {
    Mono<Response> responseMono = this.webClient
        .get()
        .uri("/router/operation/{a}/{b}", 3, 2)
        .headers(httpHeaders -> httpHeaders.set("operation", "*"))
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectNextMatches(response -> response.getOutPut() == 6)
        .verifyComplete();
  }

  @Test
  void stepVerifierPostFluxTest() {
    Mono<Response> responseMono = this.webClient
        .post()
        .uri("/router/multiply")
        .bodyValue(buildRequestDto(4, 5))
        .headers(httpHeaders -> httpHeaders.set("operation", "*"))
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectErrorMatches(throwable -> throwable instanceof Exception)
        .verify();

    //.expectErrorMatches(ex -> ex.getMessage().startsWith("No content was written")).verify();
  }

  @Test
  void lastModified() {
    ZonedDateTime now = ZonedDateTime.now();
    Mono<ServerResponse> result = ServerResponse.ok().lastModified(now).build();
    Long expected = now.toInstant().toEpochMilli() / 1000;
    StepVerifier.create(result)
        .expectNextMatches(response -> expected.equals(response.headers().getLastModified() / 1000))
        .expectComplete()
        .verify();
  }

  private MultiplyRequestDto buildRequestDto(int a, int b) {
    return new MultiplyRequestDto(a, b);
  }

}
