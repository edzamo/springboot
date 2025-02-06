package com.example.reactive.postgres.service;

import com.example.reactive.postgres.model.Vehicle;
import com.example.reactive.postgres.repository.VehiculeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
@RequiredArgsConstructor
public class VehiculeServiceImpl implements VehiculeService {

  private final VehiculeRepository repository;

  @Override
  public Flux<Vehicle> findAll() {
    return this.repository.findAll();
  }

  @Override
  public Flux<Vehicle> findAllByReleased(Integer released) {
    return this.repository.findAllByReleased(released);
  }

  @Override
  public Mono<Vehicle> findById(Long id) {
    return this.repository.findById(id);
  }

  @Override
  public Mono<Vehicle> save(Vehicle vehicle) {
    return this.repository.save(vehicle);
  }

  @Override
  public Mono<Void> deleteById(Long id) {
    return this.repository.deleteById(id);
  }

  @Override
  public Mono<Void> delete(Vehicle vehicle) {
    return this.repository.delete(vehicle);
  }
}
