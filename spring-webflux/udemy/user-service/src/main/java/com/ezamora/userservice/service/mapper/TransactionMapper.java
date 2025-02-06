package com.ezamora.userservice.service.mapper;

import com.ezamora.userservice.domain.UserTransaction;
import com.ezamora.userservice.domain.enums.TransactionsStatus;
import com.ezamora.userservice.service.dto.TransactionRequestDto;
import com.ezamora.userservice.service.dto.TransactionResponseDto;
import com.ezamora.userservice.service.dto.UserTransactionDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TransactionMapper {

  TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

  @Mapping(target = "id", ignore = true)
  @Mapping(target = "userId", source = "requestDto.userId")
  @Mapping(target = "amount", source = "requestDto.amount")
  @Mapping(target = "time",  dateFormat = "dd-MM-yyyy HH:mm:ss" , ignore = true)
  UserTransaction toUserTransaction(TransactionRequestDto requestDto);

  @Mapping(target = "userId", source = "requestDto.userId")
  @Mapping(target = "amount", source = "requestDto.amount")
  @Mapping(target = "status", source = "status")
  TransactionResponseDto toTransactionResponseDto(TransactionRequestDto requestDto,
      TransactionsStatus status);

  //@Mapping(target = "id", source = "userTransaction.id")
  @Mapping(target = "userId", source = "userTransaction.userId")
  @Mapping(target = "amount", source = "userTransaction.amount")
  @Mapping(target = "transactionDate", source = "userTransaction.time")
  UserTransactionDto toUserTransactionDto(UserTransaction userTransaction);

}
