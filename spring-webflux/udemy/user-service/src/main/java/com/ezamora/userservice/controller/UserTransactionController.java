package com.ezamora.userservice.controller;

import com.ezamora.userservice.service.UserTransactionService;
import com.ezamora.userservice.service.dto.TransactionRequestDto;
import com.ezamora.userservice.service.dto.TransactionResponseDto;
import com.ezamora.userservice.service.dto.UserTransactionDto;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("user/transaction")
@RequiredArgsConstructor
public class UserTransactionController {

  private final UserTransactionService userTransactionService;

  @PostMapping
  public Mono<TransactionResponseDto> createTransaction(
      @RequestBody Mono<TransactionRequestDto> transactionRequestDtoMono) {
    return transactionRequestDtoMono
        .flatMap(this.userTransactionService::createTransaction);
  }

  @GetMapping("{id}")
  public Flux<UserTransactionDto> findByUserId(@PathVariable("id") Integer userId) {
    return this.userTransactionService.findByUserId(userId);
       // .map(ResponseEntity::ok)
     //.defaultIfEmpty(ResponseEntity.notFound().build());
  }


}
