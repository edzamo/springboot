package com.ezamora.orderservice.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebConfig {

  @Bean
  public WebClient webClient(@Value("{product.service.url}") String url) {

    return WebClient.builder()
        .baseUrl(url)
        .defaultCookie("cookie-name", "cookie-value")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .build();
  }
}