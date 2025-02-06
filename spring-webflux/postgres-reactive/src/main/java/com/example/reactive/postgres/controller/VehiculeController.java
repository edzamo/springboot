package com.example.reactive.postgres.controller;

import com.example.reactive.postgres.model.Vehicle;
import com.example.reactive.postgres.service.VehiculeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

  private final VehiculeService service;

  @GetMapping
  public Flux<Vehicle> findAll() {
    return this.service.findAll();
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<Vehicle>> finById(@PathVariable("id") Long idVehicule) {
    return this.service.findById(idVehicule)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @GetMapping("/release/{release}")
  public Flux<Vehicle> findByRelease(@PathVariable Integer release) {
    return this.service.findAllByReleased(release);
  }

  @PostMapping
  public Mono<ResponseEntity<Vehicle>> save(@RequestBody Vehicle vehicle) {
    return this.service.save(vehicle)
        .map(ResponseEntity::ok);
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<Vehicle>> update(@PathVariable("id") Long idVehicle, @RequestBody Vehicle vehicle) {
    return this.service.findById(idVehicle)
        .flatMap(ve -> {
          ve.setModel(vehicle.getModel());
          ve.setRelease(vehicle.getRelease());
          ve.setCubicCentimeters(vehicle.getCubicCentimeters());
          return this.service.save(ve);
        })
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("{id}")
  public Mono<ResponseEntity<Void>> deleteById(@PathVariable("id") Long idVehicule) {
    return this.service.deleteById(idVehicule)
        .map(ResponseEntity::ok);
  }

}
