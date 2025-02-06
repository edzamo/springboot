package com.reactive.mongodb.reactivemongodb.controller;


import com.reactive.mongodb.reactivemongodb.model.Customer;
import com.reactive.mongodb.reactivemongodb.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("consumer")
public class CustomerController {

  @Autowired
  private CustomerService accountTemplateOperations;


  @GetMapping("all")
  public Flux<Customer> getAll() {
    return accountTemplateOperations.findAll();
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<Customer>> getCustomerByID(@PathVariable String id){
    return accountTemplateOperations.findById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<Customer> createAccount(@RequestBody Mono<Customer> accountMono) {
    return accountTemplateOperations.save(accountMono);
  }

}
