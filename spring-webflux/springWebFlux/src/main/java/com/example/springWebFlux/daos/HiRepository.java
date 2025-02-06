package com.example.springWebFlux.daos;

import com.example.springWebFlux.entity.Movie;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Repository
public class HiRepository {


    public Mono<String> hi() {
        return Mono.just("hi Asynchronous").delayElement(Duration.ofSeconds(3));
    }


    public Flux<Movie> finAllMovies() {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Polar (2019)", 64));
        movies.add(new Movie("Iron Man  (2008)", 79));
        movies.add(new Movie("The Shawshank redemption (1994)", 93));
        movies.add(new Movie("Forrest Gump (1994)", 83));
        movies.add(new Movie("Bracula (1997)", 100));

        return Flux.fromIterable(movies).delayElements(Duration.ofSeconds(2));
    }

}
