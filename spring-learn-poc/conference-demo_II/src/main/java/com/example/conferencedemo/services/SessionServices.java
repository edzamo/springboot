package com.example.conferencedemo.services;

import com.example.conferencedemo.models.Session;
import com.example.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SessionServices {

    @Autowired
    private SessionRepository repository;

    public Flux<Session> getAll() {
        return this.repository.findAll();
    }

    public Mono<Session> getSessionById(Long id) {
        return this.repository.findById(id);
    }

    public Mono<Session> saveSession(Session session){
        return this.repository.save(session);
    }
}
