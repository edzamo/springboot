package com.ezamora.userservice.controller;

import com.ezamora.userservice.service.UserService;
import com.ezamora.userservice.service.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {


  private final UserService userService;

  @GetMapping("/all")
  public Flux<UserDto> getAll() {
    return this.userService.getAll();
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<UserDto>> getUserById(@PathVariable("id") Integer userId) {
    return this.userService.getUserById(userId)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<ResponseEntity<UserDto>> createUser(@RequestBody Mono<UserDto> userDtoMono) {
    return this.userService.crateUser(userDtoMono)
        .map(userDto -> new ResponseEntity<>(userDto, HttpStatus.CREATED));
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<UserDto>> updateUser(@PathVariable("id") Integer userId,
      @RequestBody Mono<UserDto> userDtoMono) {
    return this.userService.updateUser(userId, userDtoMono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteUser(@PathVariable("id") Integer userId) {
    return this.userService.deleteUser(userId);
  }

}
