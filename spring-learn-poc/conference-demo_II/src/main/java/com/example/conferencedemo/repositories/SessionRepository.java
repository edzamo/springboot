package com.example.conferencedemo.repositories;


import com.example.conferencedemo.models.Session;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepository extends ReactiveCrudRepository<Session, Long> {

}
