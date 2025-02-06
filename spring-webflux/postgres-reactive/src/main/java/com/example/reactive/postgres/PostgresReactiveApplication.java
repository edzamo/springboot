package com.example.reactive.postgres;

import com.example.reactive.postgres.model.Vehicle;
import com.example.reactive.postgres.repository.VehiculeRepository;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.util.StreamUtils;
import reactor.core.publisher.Flux;

@SpringBootApplication
@RequiredArgsConstructor
public class PostgresReactiveApplication {

  @Value("classpath:schema.sql")
  private Resource schema;

  private final R2dbcEntityTemplate template;

  public static void main(String[] args) {
    SpringApplication.run(PostgresReactiveApplication.class, args);
  }


  @Bean
  CommandLineRunner runner(VehiculeRepository repository) throws IOException {
    String schemaSql= StreamUtils.copyToString(schema.getInputStream(), StandardCharsets.UTF_8);
    this.template.getDatabaseClient().sql(schemaSql).then().block();
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
