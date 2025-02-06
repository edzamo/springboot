package com.ezamora.webfluxdemo.webfluxdemo;

import java.net.URI;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

public class Lecture07QueryParamsTest extends BaseTests {

  @Autowired
  private WebClient webClient;

  String queryString = "http://localhost:8080/jobs/search?count={count}&page={page}";

  @Test
  void queryParamsTest() {
    URI uri = UriComponentsBuilder.fromUriString(queryString)
        .build(10, 20);

    Flux<Integer> responseMono = webClient.get()
        .uri(uri)
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectNextCount(2)
        .verifyComplete();
  }

  @Test
  void querySecondFormsParamsTest() {

    Map<String, Integer> mapValue = Map.of("count", 10,
        "page", 20);
    Flux<Integer> responseMono = webClient.get()
        .uri(uriBuilder -> uriBuilder.path("jobs/search")
            .query("count={count}&page={page}")
            //.build(4, 5))
            .build(mapValue))
        .retrieve()
        .bodyToFlux(Integer.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectNextCount(2)
        .verifyComplete();
  }
}

