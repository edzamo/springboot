package com.reactive.mongodb.reactivemongodb.repository;

import com.reactive.mongodb.reactivemongodb.model.Customer;
import java.util.List;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveCrudRepository<Customer, String> {

  public Customer findByFirstName(String firstName);

  public List<Customer> findByLastName(String lastName);

}
