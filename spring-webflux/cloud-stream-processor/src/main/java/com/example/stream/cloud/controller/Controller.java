package com.example.stream.cloud.controller;


import com.example.stream.cloud.service.Procedure;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

  private final Procedure procedure;

  @GetMapping("send/{phrase}")
  public void send(@PathVariable String phrase){
     this.procedure.send(phrase);
  }
}
