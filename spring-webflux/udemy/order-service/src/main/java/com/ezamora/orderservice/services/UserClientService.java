package com.ezamora.orderservice.services;

import com.ezamora.orderservice.services.dto.TransactionRequestDto;
import com.ezamora.orderservice.services.dto.TransactionResponseDto;
import com.ezamora.orderservice.services.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserClientService {


  Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto requestDto);

  Flux<UserDto> getAllUsers();
}
