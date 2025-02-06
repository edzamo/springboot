package com.ezamora.userservice.service.impl;

import com.ezamora.userservice.repository.UserRepository;
import com.ezamora.userservice.service.UserService;
import com.ezamora.userservice.service.dto.UserDto;
import com.ezamora.userservice.service.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  @Override
  public Flux<UserDto> getAll() {
    return this.userRepository.findAll()
        .map(UserMapper.INSTANCE::toUserDto);

  }

  @Override
  public Mono<UserDto> getUserById(Integer id) {
    return this.userRepository.findById(id)
        .map(UserMapper.INSTANCE::toUserDto);
  }

  @Override
  public Mono<UserDto> crateUser(Mono<UserDto> userDtoMono) {
    return userDtoMono
        .map(UserMapper.INSTANCE::toUser)
        .flatMap(this.userRepository::save)
        .map(UserMapper.INSTANCE::toUserDto);
  }

  @Override
  public Mono<UserDto> updateUser(Integer id, Mono<UserDto> userDtoMono) {
    return this.userRepository.findById(id)
        .flatMap(user -> userDtoMono
            .map(UserMapper.INSTANCE::toUser)
            .doOnNext(userEntity -> userEntity.setId(user.getId()))
        )
        .flatMap(this.userRepository::save)
        .map(UserMapper.INSTANCE::toUserDto);
  }

  @Override
  public Mono<Void> deleteUser(Integer id){
    return this.userRepository.deleteById(id);
  }
}
