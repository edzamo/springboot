package com.reactive.mongodb.reactivemongodb.service;

import com.reactive.mongodb.reactivemongodb.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.ReactiveRemoveOperation;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

   @Autowired
  private ReactiveMongoTemplate template;



  public Mono<Customer> findById(String id) {
    return template.findById(id, Customer.class);
  }

  public Flux<Customer> findAll() {
    return template.findAll(Customer.class);
  }

  public Mono<Customer> save(Mono<Customer> account) {
    return template.save(account);
  }

  public ReactiveRemoveOperation.ReactiveRemove<Customer> deleteAll() {
    return template.remove(Customer.class);
  }

}
