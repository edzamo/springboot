package com.ezamora.webfluxdemo.webfluxdemo;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class Lecture08AtributesTest extends BaseTests {

  @Autowired
  private WebClient webClient;

//Test check authentication depends you send
  @Test
  void stepVerifierPostFluxTest() {
    Mono<Response> responseMono = this.webClient
        .post()
        .uri("/router/multiply")
        .bodyValue(buildRequestDto(4, 5))
       // .attribute("auth", "basic")
       .attribute("auth", "oauth")
        .retrieve()
        .bodyToMono(Response.class)
        .doOnNext(System.out::println);

    StepVerifier.create(responseMono)
        .expectErrorMatches(throwable -> throwable instanceof Exception)
        .verify();

    //.expectErrorMatches(ex -> ex.getMessage().startsWith("No content was written")).verify();
  }


  private MultiplyRequestDto buildRequestDto(int a, int b) {
    MultiplyRequestDto dto = new MultiplyRequestDto(a,b);
   // dto.setFirst(a);
    //dto.setSecond(b);

    return dto;
  }

}
