package com.example.springDataR2Dbc.controller;

import com.example.springDataR2Dbc.model.Customer;
import com.example.springDataR2Dbc.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private CustomerService service;


    @GetMapping("all")
    public Flux<Customer> getAll(){
        return this.service.getAllProducts();
    }

    @GetMapping("{consumerId}")
    public Mono<ResponseEntity<Customer>> getProductById(@PathVariable int productId){
        return this.service.getProductById(productId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<Customer> createProduct(@RequestBody Mono<Customer> productMono){
        return productMono.flatMap(this.service::createProduct);
    }

    @PutMapping("{productId}")
    public Mono<Customer> updateProduct(@PathVariable int productId,
                                       @RequestBody Mono<Customer> productMono){
        return this.service.updateProduct(productId, productMono);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable int id){
        return this.service.deleteProduct(id);
    }
}
