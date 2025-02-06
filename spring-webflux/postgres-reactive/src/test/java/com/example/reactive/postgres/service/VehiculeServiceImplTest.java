package com.example.reactive.postgres.service;

import static com.example.reactive.postgres.util.MockData.getVehicle;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.example.reactive.postgres.model.Vehicle;
import com.example.reactive.postgres.repository.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
//@ContextConfiguration(classes = VehiculeServiceImpl.class)
class VehiculeServiceImplTest {

  @Mock
  private VehiculeRepository repository;
  @InjectMocks
  private VehiculeServiceImpl vehiculeService;

  @Test
  void findAll() {
      mockMongoFidAll(Flux.just(getVehicle()));
    StepVerifier.create(vehiculeService.findAll())
        .expectNextMatches(vehicle -> vehicle.getId().equals(1L))
        .verifyComplete();
  }

  private void mockMongoFidAll(Flux<Vehicle> result) {
    when(repository.findAll()).thenReturn(result);
  }

  @Test
  void findById() {
    when(repository.findById(anyLong())).thenReturn(Mono.just(getVehicle()));
    vehiculeService.findById(1L)
        .as(StepVerifier::create)
        .expectNextMatches(vehicle -> vehicle.getId().equals(1l))
        .verifyComplete();
  }

  @Test
  void save() {
    when(repository.save(any())).thenReturn(Mono.just(getVehicle()));
    vehiculeService.save(getVehicle())
        .as(StepVerifier::create)
        .expectNextMatches(vehicle -> vehicle.getId().equals(1L))
        .verifyComplete();
  }
}