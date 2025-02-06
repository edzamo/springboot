package com.spring.cloud;



/*
 * Get/{supplier}
 * Post/{consumer}
 * Post{Function}
 * */


import com.spring.cloud.dto.PersonDto;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Functions {

  @Bean
  public Function<String, String> reverse() {
    return value -> new StringBuilder(value).reverse().toString();
  }

  @Bean
  public Consumer<String> consumeText() {
    return System.out::println;
  }

  @Bean
  public Supplier<String> supplierText() {
    return () -> new String("Hi World!..");
  }

  @Bean
  public Supplier supplierPerson(){
    return  () -> new PersonDto(1,"Elvin","Manta");
  }

  @Bean
  public Supplier supplierPeople(){
    return () -> List.of(new PersonDto(1,"Elvin","Manta"),
        new PersonDto(2,"Peyton","Manta"),
    new PersonDto(1,"Charles","Manta"));
  }

}
