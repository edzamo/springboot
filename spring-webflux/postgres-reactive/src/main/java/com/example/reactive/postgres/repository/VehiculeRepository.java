package com.example.reactive.postgres.repository;

import com.example.reactive.postgres.model.Vehicle;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehiculeRepository extends R2dbcRepository<Vehicle, Long> {


  @Query("SELECT * FROM vehicles WHERE released = :released")
  Flux<Vehicle> findAllByReleased(Integer released);
}
