package com.example.conferencedemo.repositories;

import com.example.conferencedemo.models.Speakers;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpeakersRepository extends ReactiveCrudRepository<Speakers, Long> {
}
