package com.example.reactive.mongo.repository;

import com.example.reactive.mongo.model.Vehicle;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface VehiculeRepository extends ReactiveMongoRepository<Vehicle, String> {

  @Query("{'released':?0}")
  Flux<Vehicle> findAllByReleased(Integer released);
}
