package com.reactive.mongodb.reactivemongodb.repository;

import com.reactive.mongodb.reactivemongodb.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


@DataMongoTest
class CustomerRepositoryTest {


    @Autowired
    private CustomerRepository repository;


    @Test
     void setUp() throws Exception {
        Customer customer= new Customer();
        customer.setFirstName("Edwin");
        customer.setId("123");
        customer.setLastName("xxxxxxxxxx");

        repository.save(customer);
    }




}