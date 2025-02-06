package com.ezamora.userservice.repository;

import com.ezamora.userservice.domain.UserTransaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface UserTransactionRepository extends
    ReactiveCrudRepository<UserTransaction, Integer> {

 // @Query("{'userId' : ?0}")
  Flux<UserTransaction> findByUserId(Integer userId);

}
