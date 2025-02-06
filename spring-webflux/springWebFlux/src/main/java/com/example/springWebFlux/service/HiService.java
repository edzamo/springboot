package com.example.springWebFlux.service;

import com.example.springWebFlux.daos.HiRepository;
import com.example.springWebFlux.entity.Message;
import com.example.springWebFlux.entity.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@Slf4j
@RequiredArgsConstructor
public class HiService {

    private final HiRepository repository;

    public Flux<String> hola() {
        Flux<String> stringFlux= Flux.concat(repository.hi(),repository.hi());
        return stringFlux;
    }

    public Flux<Movie> finAllMovies(){
        return repository.finAllMovies()
            .log();
    }
}
