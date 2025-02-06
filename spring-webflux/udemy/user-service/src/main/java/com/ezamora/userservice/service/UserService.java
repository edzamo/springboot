package com.ezamora.userservice.service;

import com.ezamora.userservice.service.dto.UserDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

  Flux<UserDto> getAll();

  Mono<UserDto> getUserById(Integer id);

  Mono<UserDto> crateUser(Mono<UserDto> userDtoMono);

  Mono<UserDto> updateUser(Integer id, Mono<UserDto> userDtoMono);

  Mono<Void> deleteUser(Integer id);
}
