package com.example.conferencedemo.controllers;


import com.example.conferencedemo.models.Session;
import com.example.conferencedemo.services.SessionServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/sessions")
@Slf4j
public class SessionController {

    @Autowired
    private SessionServices services;

    @GetMapping
    public Flux<Session> getSession() {
        return services.getAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Mono<ResponseEntity<Session>> getSessionById(@PathVariable Long id) {
        log.info(id.toString());
        return services.getSessionById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Session> saveSession(@RequestBody Session session) {
       /* return sessionMono.map(session ->  this.services.saveSession(session))
                .flatMap(sessionMono1 -> sessionMono1);*/
        log.info(session.toString());
        return services
                .saveSession(session);
    }

    @DeleteMapping("{id}")
    public Mono<Void>  deleteSession(@PathVariable Long id){
        return services.deleteSession(id);
    }
}
