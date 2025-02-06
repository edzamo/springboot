package com.ezamora.userservice.service.impl;

import com.ezamora.userservice.domain.enums.TransactionsStatus;
import com.ezamora.userservice.repository.UserRepository;
import com.ezamora.userservice.repository.UserTransactionRepository;
import com.ezamora.userservice.service.UserTransactionService;
import com.ezamora.userservice.service.dto.TransactionRequestDto;
import com.ezamora.userservice.service.dto.TransactionResponseDto;
import com.ezamora.userservice.service.dto.UserTransactionDto;
import com.ezamora.userservice.service.mapper.TransactionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserTransactionServiceImpl implements UserTransactionService {

  private final UserRepository userRepository;
  private final UserTransactionRepository userTransactionRepository;


  //https://www.vinsguru.com/spring-data-r2dbc-transaction/
  @Override
  public Mono<TransactionResponseDto> createTransaction(final TransactionRequestDto requestDto) {
    return this.userRepository.updateUserBalance(requestDto.getUserId(), requestDto.getAmount())
        .filter(Boolean::booleanValue)
        .map(aBoolean -> TransactionMapper.INSTANCE.toUserTransaction(requestDto))
        .flatMap(this.userTransactionRepository::save)
        .map(userTransaction -> TransactionMapper.INSTANCE.toTransactionResponseDto(requestDto,
            TransactionsStatus.APPROVED))
        .defaultIfEmpty(
            TransactionMapper.INSTANCE.toTransactionResponseDto(requestDto,
                TransactionsStatus.DECLINED));
  }

  @Override
  public Flux<UserTransactionDto> findByUserId(Integer userId) {
    System.out.println("user" + userId);
    return this.userTransactionRepository.findByUserId(userId)
        .map(TransactionMapper.INSTANCE::toUserTransactionDto);
  }
}
