package com.example.learning.reactive.spring.class1.controller;

import com.example.learning.reactive.spring.class1.dto.Person;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class MitoCodeFluxAndMonoController {

    @GetMapping("/create/flux")
    public Flux<Person> fluxPerson() {
        return Flux.just(
                new Person(1, "Edwin", "Zamora", "xxx"),
                new Person(1, "Paula", "Zamora", "yyyy")
        ).log();
    }

    @GetMapping("/create/mono")
    public Mono<List<Person>> monoPerson() {
        return Flux.just(
                new Person(1, "Edwin", "Zamora", "xxx"),
                new Person(1, "Paula", "Zamora", "yyyy")
        ).collectList().log();
    }

    @GetMapping("/create/repeat")
    public Flux<Person> fluxPersonRepeat() {
        return Flux.just(
                        new Person(1, "Edwin", "Zamora", "xxx"),
                        new Person(1, "Paula", "Zamora", "yyyy")
                ).repeat(3)
                .log();
    }

    @GetMapping("/create/map")
    public Flux<Person> fluxPersonMap() {
        return Flux.just(
                new Person(1, "Edwin", "Zamora", "xxx"),
                new Person(1, "Paula", "Zamora", "yyyy")
        ).map(s -> {
            s.setFirstName(s.getFirstName().concat(" segundo"));
            return s;
        }).log();
    }

    @GetMapping("/create/mapAndFlap")
    public Flux<Integer> fluxMapAndflap() {
        return Flux.range(1, 10)
                .map(s -> s + 20)
                .flatMap(f -> Flux.just(f))
                .log();
    }

    @GetMapping("/create/flap")
    public Flux<String> fluxFlap() {
        return Flux.just(
                new Person(1, "Edwin", "Zamora", "xxx"),
                new Person(1, "Paula", "Zamora", "yyyy")
        ).flatMap(d -> {
            return Flux.just(d.getFirstName() + " ");
        }).log();

    }

    @GetMapping("/create/groupBy")
    public Flux<List<Person>> fluxGroupBy() {
        return Flux.just(
                new Person(1, "Edwin", "Zamora", "xxx"),
                new Person(1, "Paula", "Zamora", "yyyy"),
                new Person(3, "Martha", "Morocho", "yyyy")
        ).groupBy(Person::getId)
                .flatMap(ag-> ag.collectList())
                .log();

    }


}
