package com.ezamora.userservice.service;

import com.ezamora.userservice.domain.UserTransaction;
import com.ezamora.userservice.service.dto.TransactionRequestDto;
import com.ezamora.userservice.service.dto.TransactionResponseDto;
import com.ezamora.userservice.service.dto.UserTransactionDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserTransactionService {

  //https://www.vinsguru.com/spring-data-r2dbc-transaction/
  Mono<TransactionResponseDto> createTransaction(TransactionRequestDto requestDto);

  Flux<UserTransactionDto> findByUserId(Integer userId);
}
