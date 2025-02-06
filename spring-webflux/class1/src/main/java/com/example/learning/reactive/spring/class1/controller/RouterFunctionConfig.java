package com.example.learning.reactive.spring.class1.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates.*;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Component
public class RouterFunctionConfig {

    @Bean
    public RouterFunction<ServerResponse> router(SampleHandleFunction function) {
        return RouterFunctions
                .route(GET("/functional/flux")
                        .and(accept(MediaType.APPLICATION_JSON)), function::flux);


    }
}
