package com.example.springDataR2Dbc.repository;

import com.example.springDataR2Dbc.model.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository <Customer,Integer>{
}
