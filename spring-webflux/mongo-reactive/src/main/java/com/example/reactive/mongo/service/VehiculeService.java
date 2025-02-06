package com.example.reactive.mongo.service;

import com.example.reactive.mongo.model.Vehicle;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VehiculeService {

  Flux<Vehicle> findAll();

  Flux<Vehicle> findAllByReleased(Integer released);

  Mono<Vehicle> findById(String id);

  Mono<Vehicle> save(Vehicle vehicle);

  Mono<Void> deleteById(String id);

  Mono<Void> delete(Vehicle vehicle);
}
