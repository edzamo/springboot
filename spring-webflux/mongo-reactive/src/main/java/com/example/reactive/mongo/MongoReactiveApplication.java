package com.example.reactive.mongo;

import com.example.reactive.mongo.model.Vehicle;
import com.example.reactive.mongo.repository.VehiculeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class MongoReactiveApplication {

  public static void main(String[] args) {
    SpringApplication.run(MongoReactiveApplication.class, args);
  }

  @Bean
  CommandLineRunner runner(VehiculeRepository repository) {
    return args -> repository.deleteAll()
        .thenMany(Flux.just(
            Vehicle.builder()
                .id(null)
                .model("Versa")
                .cubicCentimeters(1.2)
                .release(2019)
                .build(),
            Vehicle.builder()
                .id(null)
                .model("IOniq")
                .cubicCentimeters(1.6)
                .release(2017)
                .build(),
            Vehicle.builder()
                .id(null)
                .model("Opticus")
                .cubicCentimeters(1.9)
                .release(2020)
                .build()))
        .flatMap(repository::save)
        .thenMany(repository.findAll())
        .subscribe(System.out::println);

  }

}
