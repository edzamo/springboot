package com.ezamora.webfluxdemo.service;

import com.ezamora.webfluxdemo.dto.Response;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import org.springframework.stereotype.Service;

@Service
public class MathService {

  public Response findSquare(int input) {
    return new Response(input * input);
  }

  public List<Response> multiplicationTable(int input) {
    return IntStream.rangeClosed(1, 10)
        .peek(i -> SleepUtil.sleepSeconds(1))
        .peek(i -> System.out.println("match-service  processing :" + i))
        .mapToObj(obj -> new Response(obj * obj))
        .collect(Collectors.toList());
  }
}
