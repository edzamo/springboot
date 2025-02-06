package com.example.conferencedemo.services;

import com.example.conferencedemo.models.Session;
import com.example.conferencedemo.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class SessionServices {

    @Autowired
    private SessionRepository repository;

    public Flux<Session> getAll() {
        return repository.findAll();
    }

    public Mono<Session> getSessionById(Long id) {
        return repository.findById(id);
    }

    public Mono<Session> saveSession(final Session session) {
        return repository.save(session);
    }


    public Mono<Void> deleteSession(Long id) {
        return repository.deleteById(id);
    }
}
