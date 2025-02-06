package com.ezamora.orderservice.services.impl;

import com.ezamora.orderservice.services.UserClientService;
import com.ezamora.orderservice.services.dto.TransactionRequestDto;
import com.ezamora.orderservice.services.dto.TransactionResponseDto;
import com.ezamora.orderservice.services.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserClientServiceImp implements UserClientService {

  //When you use WebClient don't need to @Autowired only call with final
  private final WebClient webClient;
  @Value("${user.service.url}")
  private String injectedPropertyUrl;

  @Override
  public Mono<TransactionResponseDto> authorizeTransaction(TransactionRequestDto requestDto) {
    return this.webClient.post()
        .uri(injectedPropertyUrl.concat("transaction"))
        .bodyValue(requestDto)
        .retrieve()
        .bodyToMono(TransactionResponseDto.class);
  }

  @Override
  public Flux<UserDto> getAllUsers(){
    return this.webClient
        .get()
        .uri(injectedPropertyUrl.concat("all"))
        .retrieve()
        .bodyToFlux(UserDto.class);

  }

}
