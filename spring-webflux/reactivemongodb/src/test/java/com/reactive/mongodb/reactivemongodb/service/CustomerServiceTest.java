package com.reactive.mongodb.reactivemongodb.service;

import com.reactive.mongodb.reactivemongodb.model.Customer;
import com.reactive.mongodb.reactivemongodb.repository.CustomerRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.ReactiveRemoveOperation;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CustomerServiceTest {

    @Autowired
    private CustomerService serviceTemplate;

    @MockBean
    private CustomerRepository repository;

    @Test
    void findById() {
        //Set up our Mock
        doReturn(Mono.just(new Customer("1", "xxx", "yy"))).when(repository).findById("1");

        Mono<Customer> customer=  serviceTemplate.findById("1");
        customer.map(c -> {
            Assert.assertNotNull(c.id.isEmpty());
            return c;
        });

    }

    @Test
    void findAll() {
        //Set up our Mock
        doReturn(Mono.just(new Customer("1", "xxx", "yy"))).when(repository).findById("1");

        Flux<Customer> customers=  serviceTemplate.findAll();
        customers.map(c -> {
            Assert.assertNotNull(c.id.isEmpty());
            return c;
        });

    }

    @Test
    void save() {

        //Set up our Mock
        doReturn(Mono.just(new Customer("1", "xxx", "yy"))).when(repository).save(any());
        Mono<Customer> customer=  serviceTemplate.save(Mono.just(new Customer("","","")));
        customer.map(c -> {
            Assert.assertNotNull(c.id.isEmpty());
            return c;
        });

    }

    @Test
    void deleteAll() {

        //Set up our Mock
        doReturn(Mono.just(new Customer("1", "xxx", "yy"))).when(repository).delete(any());

       ReactiveRemoveOperation.ReactiveRemove<Customer> customers= serviceTemplate.deleteAll();


    }


}