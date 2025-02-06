package com.example.springWebFlux;


import com.example.springWebFlux.entity.Movie;
import com.example.springWebFlux.service.HiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MessageController {

  private final HiService service;

  @GetMapping("/hi")
  public Flux<String> getMessage() {
    log.info("Start Service [{}]");
    return this.service.hola();
  }

  @GetMapping("/allMovies")
  public Flux<Movie> finAllMoviesWithDelay() {
    return service.finAllMovies();
  }

}
