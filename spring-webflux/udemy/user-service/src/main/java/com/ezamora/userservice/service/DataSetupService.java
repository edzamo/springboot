package com.ezamora.userservice.service;

import java.nio.charset.StandardCharsets;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.Resource;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

@Service
@RequiredArgsConstructor
public class DataSetupService implements CommandLineRunner {


  @Value("classpath:sql/init.sql")
  private Resource resourceInit;
  private final R2dbcEntityTemplate entityTemplate;

  @Override
  public void run(String... args) throws Exception {
    System.out.println("database");

    String query = StreamUtils.copyToString(resourceInit.getInputStream(), StandardCharsets.UTF_8);
    System.out.println(resourceInit);

    this.entityTemplate
        .getDatabaseClient()
        .sql(query)
        .then()
        .subscribe();

  }
}
