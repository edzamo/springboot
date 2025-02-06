package com.ezamora.webfluxdemo.config;

import com.ezamora.webfluxdemo.dto.MultiplyRequestDto;
import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.exception.InputValidationException;
import com.ezamora.webfluxdemo.service.ReactiveMathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class RequestHandler {

  @Autowired
  private ReactiveMathService reactiveMathService;

  public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
    int input = Integer.parseInt(serverRequest.pathVariable("input"));
    Mono<Response> responseMono = this.reactiveMathService.findSquare(input);
    return ServerResponse.ok().body(responseMono, Response.class);
  }

  public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
    int input = Integer.parseInt(serverRequest.pathVariable("input"));
    Flux<Response> responseFlux = this.reactiveMathService.multiplicationTable(input);
    return ServerResponse.ok().body(responseFlux, Response.class);
  }

  public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
    int input = Integer.parseInt(serverRequest.pathVariable("input"));
    Flux<Response> responseFlux = this.reactiveMathService.multiplicationTable(input);
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(responseFlux, Response.class);
  }

  public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
    Mono<MultiplyRequestDto> multiplyRequestDtoMono = serverRequest.bodyToMono(
        MultiplyRequestDto.class);
    System.out.println("serverRequest.headers()" + serverRequest.headers());
    Mono<Response> responseMono = this.reactiveMathService.multiply(multiplyRequestDtoMono);
    return ServerResponse.ok()
        .contentType(MediaType.TEXT_EVENT_STREAM)
        .body(responseMono, Response.class);
  }

  public Mono<ServerResponse> squareHandlerWithValidation(ServerRequest serverRequest) {
    int input = Integer.parseInt(serverRequest.pathVariable("input"));
    if (input < 10 || input > 20) {
      return Mono.error(new InputValidationException(input));
    }
    Mono<Response> responseMono = this.reactiveMathService.findSquare(input);
    return ServerResponse.ok().body(responseMono, Response.class);
  }

  public Mono<ServerResponse> operationFunctional(ServerRequest serverRequest) {
    try {
      int inputA = Integer.parseInt(serverRequest.pathVariable("a"));
      int inputB = Integer.parseInt(serverRequest.pathVariable("b"));
      String operation = serverRequest.headers().firstHeader("operation");
      System.out.println("operation: " + operation);
      return this.reactiveMathService.operationsHandler(inputA, inputB, operation);
    } catch (Exception e) {
      return ServerResponse.badRequest()
          .bodyValue("You need a 'operation' in header  +,-,*,/ ");
    }


  }


}
