package com.ezamora.webfluxdemo.service;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ReactiveMathService {


  public Mono<Response> findSquare(int input) {
    return Mono.fromSupplier(() ->
            input * input
        )
        .map(Response::new);
  }

  //This is a reactive Code
  public Flux<Response> multiplicationTable(int input) {
    return Flux.range(1, 10)
        .doOnNext(i -> SleepUtil.sleepSeconds(1))
        .doOnNext(i -> System.out.println("reactive-math-service processing :" + i))
        .map(m -> new Response(input * input));
  }

  /* Example code no reactive because the whole business of logic, no is including for this reason no executing in the pipeline
  The things will no stop because the all the operation is getting executed outside the pipeline


  /*
  public Flux<Response> multiplicationTable(int input) {
    List<Response> list = IntStream.rangeClosed(1, 10)
        .mapToObj(value -> new Response(value * input))
        .collect(Collectors.toList());
    return Flux.fromIterable(list);
  }

  */


  public Mono<Response> multiply(Mono<MultiplyRequestDto> dtoMono) {
    return dtoMono
        .doOnNext(d -> System.out.println(" multiply dtoMono..:" + d))
        .map(dto -> dto.getFirst() * dto.getSecond())
        .map(Response::new);

  }


  public Mono<ServerResponse> operationsHandler(int inputA, int inputB, String operation) {
    return switch (operation) {
      case "+" -> getServerResponse(inputA + inputB);
      case "-" -> getServerResponse(inputA - inputB);
      case "*" -> getServerResponse(inputA * inputB);
      case "/" -> getServerResponse(inputA / inputB);
      default -> ServerResponse.badRequest().bodyValue("operation should be +,-,*,/");
    };
  }

  private static Mono<ServerResponse> getServerResponse(int inputA) {
    return Mono.fromSupplier(
            () -> ServerResponse.ok().body(Mono.just(new Response(inputA)), Response.class))
        .flatMap(serverResponseMono -> serverResponseMono);
  }

}
