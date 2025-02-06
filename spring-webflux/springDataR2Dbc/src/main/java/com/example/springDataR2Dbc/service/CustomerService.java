package com.example.springDataR2Dbc.service;

import com.example.springDataR2Dbc.model.Customer;
import com.example.springDataR2Dbc.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository repository;

    public Flux<Customer> getAllProducts(){
        return this.repository.findAll();
    }

    public Mono<Customer> getProductById(int productId){
        return this.repository.findById(productId);
    }

    public Mono<Customer> createProduct(final Customer product){
        return this.repository.save(product);
    }

    public Mono<Customer> updateProduct(int customerId, final Mono<Customer> customerMono){
        return this.repository.findById(customerId)
                .flatMap(p -> customerMono.map(u -> {
                    p.setFirstname(u.getFirstname());
                    p.setLastname(u.getLastname());
                    return p;
                }))
                .flatMap(p -> this.repository.save(p));
    }

    public Mono<Void> deleteProduct(final int id){
        return this.repository.deleteById(id);
    }


}
