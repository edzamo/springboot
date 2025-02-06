package com.ezamora.webfluxdemo.controller;

import com.ezamora.webfluxdemo.dto.Response;
import com.ezamora.webfluxdemo.service.MathService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("math")
public class MatchController {

  @Autowired
  private MathService mathService;

  @GetMapping("/square/{input}")
  public Response findSquare(@PathVariable int input){
    return  this.mathService.findSquare(input);
  }

  @GetMapping("/table/{input}")
  public List<Response> multiplicateTable(@PathVariable int input){
   return  mathService.multiplicationTable(input);
  }

}
