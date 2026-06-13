package com.example.ia.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ia.webflux.service.ChatService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/songs")
@AllArgsConstructor
@Slf4j
public class SongsController {

  private final ChatService chatService;

  @GetMapping("/ai/{query}")
  public Mono<String> getSongs(@PathVariable("query") String query) {
    log.info("Received query: {}", query);
    return Mono.just(
      chatService.chatTemplate(query)
    );
  }

  @GetMapping("/ai/stream")
  public Mono<String> getSongs(@RequestParam("query") String query, @RequestParam("queryTwo") String queryTwo) {
    log.info("Received query: {}", query);
    log.info("Received queryTwo: {}", queryTwo);
    return Mono.just(query + " " + queryTwo );
  }
}
