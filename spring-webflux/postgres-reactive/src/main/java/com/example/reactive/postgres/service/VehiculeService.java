package com.example.reactive.postgres.service;

import com.example.reactive.postgres.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehiculeService {

  Flux<Vehicle> findAll();

  Flux<Vehicle> findAllByReleased(Integer released);

  Mono<Vehicle> findById(Long id);

  Mono<Vehicle> save(Vehicle vehicle);

  Mono<Void> deleteById(Long id);

  Mono<Void> delete(Vehicle vehicle);
}
