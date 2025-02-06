package com.example.reactive.mongo.service;

import static com.example.reactive.mongo.util.MockData.getVehicle;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.example.reactive.mongo.model.Vehicle;
import com.example.reactive.mongo.repository.VehiculeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
 // @ContextConfiguration(classes = VehiculeServiceImpl.class)
class VehiculeServiceImplTest {

  @Mock
  private VehiculeRepository repository;
  //@InjectMocks
  @InjectMocks
  private VehiculeServiceImpl vehiculeService;
  @Test
  void findAll() {
      mockMongoFidAll(Flux.just(getVehicle()));
    StepVerifier.create(vehiculeService.findAll())
        .expectNextMatches(vehicle -> vehicle.getId().equals("66956a9ddc88461a519b1bcb"))
        .verifyComplete();

  }

  private void mockMongoFidAll(Flux<Vehicle> result) {
    when(repository.findAll()).thenReturn(result);
  }

  @Test
  void findById() {
    when(repository.findById(anyString())).thenReturn(Mono.just(getVehicle()));
    vehiculeService.findById("66956a9ddc88461a519b1bcb")
        .as(StepVerifier::create)
        .expectNextMatches(vehicle -> vehicle.getId().equals("66956a9ddc88461a519b1bcb"))
        .verifyComplete();
  }

  @Test
  void save() {
    when(repository.save(any())).thenReturn(Mono.just(getVehicle()));
    vehiculeService.save(getVehicle())
        .as(StepVerifier::create)
        .expectNextMatches(vehicle -> vehicle.getId().equals("66956a9ddc88461a519b1bcb"))
        .verifyComplete();
  }
}