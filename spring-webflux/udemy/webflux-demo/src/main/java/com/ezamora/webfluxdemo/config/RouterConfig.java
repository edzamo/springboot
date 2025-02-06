package com.ezamora.webfluxdemo.config;

import com.ezamora.webfluxdemo.dto.InputFailedValidationResponse;
import com.ezamora.webfluxdemo.exception.InputValidationException;
import java.util.function.BiFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class RouterConfig {

  @Autowired
  private RequestHandler requestHandler;

  @Bean
  public RouterFunction<ServerResponse> highLevelRouter() {
    return RouterFunctions
        .route()
        .path("router", this::serverResponseRouterFunction)
        .build();
  }

  //  @Bean
  public RouterFunction<ServerResponse> serverResponseRouterFunction() {
    return RouterFunctions
        .route()
        // .GET("router/square/{input}", requestHandler::squareHandler)
        .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")),
            requestHandler::squareHandler)
        .GET("square/{input}",
            request -> ServerResponse.badRequest().bodyValue("only 10-19 allowed "))
        .GET("square/{input}", requestHandler::squareHandler)
        .GET("table/{input}", requestHandler::tableHandler)
        .GET("table/{input}/stream", requestHandler::tableStreamHandler)
        .POST("multiply", requestHandler::multiplyHandler)
        .GET("square/{input}/validation", requestHandler::squareHandlerWithValidation)
        .GET("operation/{a}/{b}", requestHandler::operationFunctional)
        .onError(InputValidationException.class, exceptionHandler())
        .build();
  }

  private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
    return (err, req) -> {
      InputValidationException ex = (InputValidationException) err;
      InputFailedValidationResponse response = new InputFailedValidationResponse();
      response.setInput(ex.getInput());
      response.setErrorCode(ex.getErrorCode());
      response.setMessage(ex.getMessage());
      return ServerResponse.ok().bodyValue(response);
    };
  }

}
